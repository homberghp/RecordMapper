package genericmapper;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author hom
 */
public class GeneratorMapperCastTest {
  
    
    //@Disabled("think TDD")
    @Test
    public void tCasts() {
        MapperGenerator gen= new MapperGenerator(testentities.Car.class);
        String javaSource = gen.javaSource();
        System.out.println( "javaSource = " + javaSource );
        assertThat(javaSource). contains("(int)","(String[])");
//        fail( "method tmethod reached end. You know what to do." );
    }
}
