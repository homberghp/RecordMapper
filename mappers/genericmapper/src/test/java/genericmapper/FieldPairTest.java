package genericmapper;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import testutils.TestUtils;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class FieldPairTest {

    //@Disabled("Think TDD")
    @Test
    void tEqualsHashCodeToSatisfyCoverage() {
        Object o = new Object();
        Object o2 = new Object();
        FieldPair a = new FieldPair( "Hi", o );
        FieldPair a1 = new FieldPair( "Hi", o2 );
        FieldPair b = new FieldPair( "Bye", o );
        TestUtils.verifyEqualsAndHashCode( a, a1, b );
//        fail( "method EqualsHashCodeToSatisfyCoverage completed succesfully; you know what to do" );
    }

}
