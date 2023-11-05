package genericmapper;

import static genericmapper.Constants.generatedJavaFileName;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Helper for mapper plugin. This class accepts the parameters of the plugin and
 * passes it to individual MapperGenarator instances.
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class MapperGeneratorRunner {

    public static void main( String[] args ) {
        String pOutDir = System.getProperty( "mapper.generator.outDir", "out" );
        String pClassesDir = System.getProperty( "mapper.generator.classesDir",
                "target" + fileSep + "classes" );
        String[] packNames;
        if ( args.length > 0 ) {
            packNames = args;
        } else {
            packNames = new String[]{ "entities" };
        }
//        System.out.printf( "running with params classesdir: %s, outdir: %s, and packages: %s", pClassesDir, pOutDir, packNames );
        new MapperGeneratorRunner( pClassesDir, pOutDir, packNames ).run();
    }

    final String classesDir;
    private final String outDir;
    final String[] packNames;

    public MapperGeneratorRunner( String baseDir, String outDir, String[] packNames ) {
        this.classesDir = baseDir;
        this.outDir = outDir;
        this.packNames = packNames;

    }

    public void run() {
        try {
            for ( String packName : packNames ) {
                System.out.println( "generating for package " + packName );
                generateMappers( outDir, classesDir,
                        getCanditateEntityNames(
                                classesDir, packName ) );
            }
        } catch ( IOException | ClassNotFoundException ex ) {
            Logger.getLogger( MapperGenerator.class.getName() )
                    .log( Level.SEVERE, null, ex );
        }
    }

    /**
     * Get the list of candidate entities from the compiled classes
     * directory.The method removes the following file name patterns from the
     * available files below the start directory.
     * <ul>
     * <li>any filename containing a dash, such as in doc-info.class or
     * module-info.class</li>
     * <li>Any class name ending in Mapper.class to avoid mapping the
     * mapper.</li>
     * </ul>
     *
     * @param startDir to start
     * @param entPackage package for entities
     * @return list of possible entity classes.
     *
     * @throws IOException dir
     */
    public Set<String> getCanditateEntityNames( String startDir,
            String entPackage )
            throws IOException {
        Path startPath = Path.of( startDir );
        Path root = Path.of(
                startDir + fileSep + entPackage.replace( ".", fileSep ) );
        if ( Files.exists( root ) ) {
            try (  Stream<Path> stream = Files.walk( root,
                    Integer.MAX_VALUE ) ) {
                return stream
                        .filter( file -> !Files.isDirectory( file ) )
                        .filter( f -> !fileNameContains( f, "-" ) ) // avoid info files
                        .filter( f -> !fileNameEndsWith( f, "Mapper.class" ) )
                        .filter( file -> fileNameEndsWith( file, ".class" ) )
                        .map( p -> startPath.relativize( p ) )
                        .map( Path::toString )
                        .map( s -> s.substring( 0,
                        s.length() - ".class".length() ) )
                        .map( s -> s.replace( fileSep, "." ) )
                        .peek( c -> System.out.println("[INFO] generating mapper for entitiy class '"+c+"'"))
                        .collect( Collectors.toSet() );
            }
        } else {
            System.out.println( "no classes found under root " + root);
            return Collections.emptySet();
        }
    }

    static boolean fileNameEndsWith( Path file, String end ) {
        return file.getFileName().toString().endsWith( end );
    }

    static boolean fileNameContains( Path file, String needle ) {
        return file.getFileName().toString().contains( needle );
    }

    static String pathSep = System.getProperty( "path.separator" );
    static String fileSep = System.getProperty( "file.separator" );

    void generateMappers( String outDir, String classPathElement, Collection<String> entityNames ) throws
            ClassNotFoundException, FileNotFoundException, MalformedURLException {
        URLClassLoader cl = new URLClassLoader( new URL[]{
            Path.of( classesDir ).toUri().toURL()
        } );
        for ( String entityName : entityNames ) {
            Class<?> clz = Class.forName( entityName, true, cl );
            String fileName = generatedJavaFileName( outDir, clz );
            File dir = new File( fileName );
            dir.getParentFile().mkdirs();
            String javaSource = new MapperGenerator( clz ).javaSource();
            if ( !javaSource.isBlank() ) {
                try (  PrintStream out = new PrintStream( fileName ) ) {
                    out.print( javaSource );
                    out.flush();
                }
            }
        }
    }
}
