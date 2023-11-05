
package genericmapper;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author hom
 */
public class CastExpressionTest {
    
    String[] stringa ={};
    Integer integer=1;
    class Car{};
    
    Car[] cars={};
    //@Disabled("think TDD")
    @Test
    public void tNameWithArray() {
        String simpleOrQualifiedName = MapperGenerator.castExpression( stringa.getClass() );
        assertThat(simpleOrQualifiedName).isEqualTo( "(String[])");
//        fail( "method tNameWithArray reached end. You know what to do." );
    }
    
    //@Disabled("think TDD")
    @Test
    public void tSimpleClass() {
        String simpleOrQualifiedName = MapperGenerator.castExpression( integer.getClass() );
        assertThat(simpleOrQualifiedName).isEqualTo( "(Integer)");
//        fail( "method tSimpleClass reached end. You know what to do." );
    }

    
    //@Disabled("think TDD")
    @Test
    public void tCustumField() {
        String simpleOrQualifiedName = MapperGenerator.castExpression( cars.getClass() );
        assertThat(simpleOrQualifiedName).isEqualTo( "(genericmapper.CastExpressionTest$Car[])");
//        fail( "method tCustumField reached end. You know what to do." );
    }
}
