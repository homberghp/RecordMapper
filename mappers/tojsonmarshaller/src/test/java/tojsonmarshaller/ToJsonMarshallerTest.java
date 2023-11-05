package tojsonmarshaller;

import testentities.Student;
import genericmapper.Mapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import static tojsonmarshaller.ToJsonMarshaller.asJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testentities.Car;
import testentities.Engine;
import static tojsonmarshaller.TestData.*;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class ToJsonMarshallerTest {

    @BeforeAll
    static void loadMapper() throws ClassNotFoundException {
        Class<?> forName = Class.forName( "client.StudentMapper" );
        System.out.println( "loaded studentmapper " + forName.getName() );
    }

    Class<Student> clz = Student.class;
    Mapper<Student, Object> studentMapper = Mapper.mapperFor( clz );

    //@Disabled("Think TDD")
    @ParameterizedTest
    @MethodSource( "atoms" )
    public void tAtomToJson( Object number, String expected ) {
        int n = 42;
        String asJson = ToJsonMarshaller.asJson( number );
        assertThat( asJson ).isEqualTo( expected );
//        fail( "method Atom completed succesfully; you know what to do" );
    }

    static Stream<Arguments> atoms() {
        return Stream.of(
                // TODO add other atom tests
                arguments( 123, "123" ),
                arguments( 123.45D, "123.45" ),
                arguments( 123.45F, "123.45" ),
                arguments( true, "true" ),
                arguments( 12345L, "12345" ),
                arguments( "Hello", "\"Hello\"" ),
                arguments( LocalDate.of( 2011, 8, 11 ), "\"2011-08-11\"" ),
                arguments( LocalDateTime.of( 2011, 8, 11, 12, 34, 56 ),
                        "\"2011-08-11T12:34:56\"" )
        );
    }

//    @Disabled("Think TDD")
    @Test
    void refTypeToJson() {
//        JsonMarshaller<Student> m = JsonMarshaller.forType(  Student.class );
        String asJson = asJson( jan );
        System.out.println( "asJson = '" + asJson + '\'' );
        assertThat( asJson ).contains( "{", "}",
                quotePair( "firstname", firstName ),
                quotePair( "lastname", lastName ),
                quotePair( "dob", dob ),
                quotePair( "email", email ),
                quotePair( "cohort", cohort ),
                quotePair( "active", active )
        );
//        fail( "refTypeToJson refTypeToJson reached end. You know what to do." );
    }

    //@Disabled("Think TDD")
    @Test
    void tPrimitiveArrayToJson() {
        int[] nums = { 1, 2, 3, 4, 5 };
        String asJson = asJson( nums );
        assertThat( asJson ).contains( "[", "1", "2", "3", "4", "5", "]" );
//        fail( "method tPrimitiveArrayToJson completed succesfully; you know what to do" );
    }

    @Test

    void refArrayToJson() {
//        JsonMarshaller<Student> m = JsonMarshaller.forType(  Student.class );
        Student[] studs = { jan, piet };
        String asJson = asJson( studs );
        System.out.println( "asJson = '" + asJson + '\'' );
        assertThat( asJson ).contains( "[",
                "{",
                quotePair( "firstname", firstName ),
                quotePair( "lastname", lastName ),
                quotePair( "dob", dob ),
                quotePair( "email", email ),
                quotePair( "cohort", cohort ),
                quotePair( "active", active ),
                "}",
                quotePair( "active", active ),
                "}", "]"
        );
//        fail( "refArrayToJson refTypeToJson reached end. You know what to do." );
    }

    //@Disabled("Think TDD")
    @Test
    void trefCollection() {
        List<Student> studs = List.of( jan, piet );
        String asJson = asJson( studs );
        System.out.println( "asJson = '" + asJson + '\'' );
        assertThat( asJson ).contains( "[",
                "{",
                quotePair( "firstname", firstName ),
                quotePair( "lastname", lastName ),
                quotePair( "dob", dob ),
                quotePair( "email", email ),
                quotePair( "cohort", cohort ),
                quotePair( "active", active ),
                "}", ",",
                "{",
                quotePair( "firstname", "Piet" ),
//                quotePair( "tussenVoegsel", null ),
                "}",
                "]"
        );
//        fail( "method refCollection completed succesfully; you know what to do" );
    }

    static String quotePair( String key, Object value ) {
        return ToJsonMarshaller.pairToString( key, value );
    }

    /**
     * Test recursive toJson.
     */
    //@Disabled("Think TDD")
    @Test
    void carToJson() {

        Engine m = new Engine( "Diesel", 6, 250.0D );
        Car c = new Car( "BMW", "Black", m );

        String carJson = ToJsonMarshaller.asJson( c );
        System.out.println( "carJson = " + carJson );

        assertThat( carJson ).contains( "Diesel", "\"cilinders\":6" );

//        fail( "method carToJson reached end. You know what to do." );
    }

    //@Disabled("Think TDD")
    @Test
    void tEmptyCollection() {
        String asJson = ToJsonMarshaller.asJson( List.of() );
        assertThat( asJson ).isEqualTo( "[]" );
//        fail( "method EmptyCollection completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tNull() {
        String asJson = ToJsonMarshaller.asJson( null );
        assertThat( asJson ).isEqualTo( "null" );
//        fail( "method Null completed succesfully; you know what to do" );
    }

}
