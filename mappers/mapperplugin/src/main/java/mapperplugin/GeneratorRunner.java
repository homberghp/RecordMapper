package mapperplugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Generates mappers for named Types. The types are fully qualified types to be
 * read from the class path.
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class GeneratorRunner {

    final String classesDir;
    final List<String> packNames;
    final String outDir;
    final String jars;

    public GeneratorRunner( String classesDir, String outDir, List<String> packNames, String jars ) {
        this.classesDir = classesDir;
        this.outDir = outDir;
        this.packNames = packNames;
        this.jars = jars;
    }

    void run() throws IOException, InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();
        String[] commandParams = {
            "java",
            "-Dmapper.generator.outDir=" + outDir,
            "-Dmapper.generator.classesDir=" + classesDir,
            "-cp", jars + ":" + classesDir,
            "genericmapper.MapperGeneratorRunner"
        };
        String[] packageNames = packNames.toArray( String[]::new );
        String[] allParams = joinArrays( commandParams, packageNames );
        processBuilder.command( allParams );
        processBuilder.command().forEach( c -> System.out.println("[INFO] "+c));
        Process process = processBuilder.start();
        BufferedReader reader
                = new BufferedReader( new InputStreamReader( process
                        .getInputStream() ) );
        String line;
        while ( ( line = reader.readLine() ) != null ) {
            System.out.println( line );
        }

        int exitCode = process.waitFor();
//        System.out.println( "\nExited with error code : " + exitCode );
    }

    private String[] joinArrays( String[] commandParams, String[] packageNames ) {
        String[] result = Arrays.copyOf( commandParams,
                commandParams.length + packageNames.length );
        System.arraycopy( packageNames, 0, result, commandParams.length,
                packageNames.length );
        return result;

    }

}
