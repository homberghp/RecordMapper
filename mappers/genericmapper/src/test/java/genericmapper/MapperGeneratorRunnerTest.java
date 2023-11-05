package genericmapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * Give MapperGeneratorRunner a test run.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class MapperGeneratorRunnerTest {

    @Test
    public void tRunGenerator() throws IOException {
        Path tmpDir = Files.createTempDirectory( "puk" );
        String tmpName=tmpDir.toString();
        assertThat( tmpDir ).exists();
        System.setProperty( "mapper.generator.classesDir", "target/test-classes" );
        System.setProperty( "mapper.generator.outDir", tmpDir.toString()  );
        String[] args = { "testentities" };

        MapperGeneratorRunner.main( args );
        
        String expectedFile=String.join( "/", tmpName, "testentities", "CourseMapper.java"); 
        assertThat(Path.of( expectedFile)).exists();
//        fail( "testSomeMethod completed. You know what to do."  );
    }

    @Test
    public void tRunGeneratorNoArgs() throws IOException {
        Path tmpDir = Files.createTempDirectory( "puk" );
        String tmpName=tmpDir.toString();
        assertThat( tmpDir ).exists();
        System.setProperty( "mapper.generator.classesDir", "target/test-classes" );
        System.setProperty( "mapper.generator.outDir", tmpDir.toString()  );
        String[] args = {};

        MapperGeneratorRunner.main( args );
        
        String expectedFile=String.join( "/", tmpName, "entities", "EmployeeMapper.java"); 
        assertThat(Path.of( expectedFile)).exists();
//        fail( "testSomeMethod completed. You know what to do."  );
    }

}
