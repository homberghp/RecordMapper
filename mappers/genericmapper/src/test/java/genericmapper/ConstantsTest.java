package genericmapper;

import java.lang.reflect.Field;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class ConstantsTest {

    String fieldWithName;

    //@Disabled("Think TDD")
    @ParameterizedTest
    @CsvSource( {
        "BEAN,getFieldWithName()",
        "RECORD,fieldWithName()", } )

    void tFieldStrategy( String strategy, String expected ) {
        Field f = getClass().getDeclaredFields()[ 0 ];
        assertThat( Constants.getterName(strategy, f ) ).isEqualTo( expected );
//        fail( "method FieldStrategy completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tjavaFileName() {
        String generatedJavaFileName = Constants.generatedJavaFileName( "out",
                                                                        getClass() );
        assertThat( generatedJavaFileName ).endsWith( 
                "genericmapper/ConstantsTestMapper.java" );
//        fail( "method javaFileName completed succesfully; you know what to do" );
    }
}
