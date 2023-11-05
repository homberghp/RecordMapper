package csvfilepersistence;

import genericmapper.Mapper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
//import nl.fontys.sebivenlo.dao.memory.InMemoryDAO;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 * @param <E>
 */
public class CsvPersitor<K, E> implements DAO<K, E> {

    private final ConcurrentMap<K, E> storage = new ConcurrentHashMap<K, E>();

    private final String storageName;
    private final Class<E> entityType;

    public CsvPersitor( String storageName, Class<E> entityType ) {
        this.storageName = storageName;
        this.entityType = entityType;
        if ( Files.exists( Paths.get( this.storageName ) ) ) {
            System.out.println( "loaded " + storageName );
            this.load( this.storageName );
        }
        Thread saveThread = new Thread( () -> persistToDisk() );
        Runtime.getRuntime().addShutdownHook( saveThread );
    }

    public CsvPersitor( Class<E> entityType ) {
        this( entityType.getName() + "csv", entityType );
    }

    public E get( K k ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void save( E e ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete( K k ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public List<E> getAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    private void persistToDisk() {
        if ( storage.isEmpty() ) {
            return; // nothing to do
        }
        try ( PrintStream out = new PrintStream( getStorageName() ); ) {
            this.storage.values()
                    .stream()
                    .map( this::entityToString )
                    .forEach( out::println );
        } catch ( FileNotFoundException ex ) {
            Logger.getLogger( CsvPersitor.class.getName() )
                    .log( Level.SEVERE, null, ex );
        }
    }

    String entityToString( E e ) {
        Mapper<E> mapper = Mapper.mapperFor( entityType );
        return Stream.of( mapper.deconstruct( e ) )
                .flatMap( Stream::of )
                .map( x -> quoteWhenNeeded( x, '"' ) ).collect( joining( "," ) );
    }

    String quoteWhenNeeded( Object e, char quote ) {
        if ( e == null ) {
            return "";
        }
        if ( e instanceof Number ) {
            return e.toString();
        }
        return quote + e.toString() + quote;
    }

    private void load( String aStorageName ) {
        Mapper<E> mapper = Mapper.mapperFor( entityType );
        try ( Stream<String> lines = Files.lines( Path.of( aStorageName ) ) ) {
            this.storage.clear();
            lines.map( s-> s.split("\\s*,\\s*"))
                    .map(mapper::construct)
                    .;
//                    lengthmapper::construct )
                    ;
        } catch ( FileNotFoundException ex ) {
            Logger.getLogger( CsvPersitor.class.getName() ).
                    log( Level.SEVERE, null, ex );
        } catch ( IOException | ClassNotFoundException ex ) {
            Logger.getLogger( CsvPersitor.class.getName() ).
                    log( Level.SEVERE, null, ex );
        }

    }

    /**
     * Get the storage name.
     *
     * @return the name
     */
    String getStorageName() {
        return this.storageName;
    }

    public int size() {
        return storage.size();
    }

}
