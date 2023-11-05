package genericmapper;

import java.lang.reflect.Field;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * Naming the getters in the generated and assumed getters.
 *
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public enum GetterNamingStrategy implements Function<Field,String> {

    /**
     * Use BEAN for traditional getXXX where xXX is field name.
     *
     */
    BEAN{
        @Override
        public String apply(Field f) {
            String name=f.getName();
            
            char fLetter=Character.toUpperCase( name.charAt( 0) );
            String remainder=name.substring( 1);
            return "get"+fLetter+remainder+"()";
        }
    },
    /**
     * Use RECORD for java 14+ record style entities.
     */
    RECORD{
        @Override
        public String apply(Field f) {
//            System.out.println( "record style" );
            return f.getName()+"()";
        }

    };
}
