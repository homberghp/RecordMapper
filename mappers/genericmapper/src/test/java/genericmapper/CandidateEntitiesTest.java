package genericmapper;

import java.io.IOException;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
 
/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class CandidateEntitiesTest {

    MapperGeneratorRunner runner= new MapperGeneratorRunner(".", "out", new String[]{"testentities"});
    //@Disabled("Think TDD")
    @Test
    void tCandidateFiles() throws IOException {
        Set<String> canditateEntityNames = runner.getCanditateEntityNames( "target/classes", "testentities");
        assertThat(canditateEntityNames).doesNotContain( "genericmapper.Constants","module-info");
//        fail( "method CandidateFiles completed succesfully; you know what to do" );
    }
    
}