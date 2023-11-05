package genericmapper;

import static genericmapper.TestData.jan;
import static genericmapper.TestData.sData;
import static genericmapper.TestData.snummer;
import java.lang.reflect.Field;
import static java.time.LocalDate.of;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import testentities.Course;
import testentities.Student;
import testentities.Tutor;

/**
 * For TestData see separate test data class.
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class MapperTest {

    @BeforeAll
    static void loadMapper() throws ClassNotFoundException {
        Mapper.addModulesToWatch( "org.assertj.core.api" );
        Class<?> forName = Class.forName( "testentities.StudentMapper" );
        System.out.println( "loaded studentmapper " + forName.getName() );
    }

    Mapper< Student, Integer> mapper = Mapper
            .mapperFor( Student.class );

    /**
     * Assert that constructed xJan equals the real jan.
     */
//    @Disabled( "Think TDD" )
    @Test
    public void tStudentMapperConstructs() {
        //Start Solution::replacewith:://TODO construct jan from sData
        Student xJan = mapper.construct( sData );
        assertThat( xJan ).isEqualTo( jan );
        //End Solution::replacewith::fail( "method StudentMapper completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    public void tStudentMapperDeconstructs() {
        //Start Solution::replacewith:://TODO decontruct jan and check you have sData back
        Object[] deconstruct = mapper.deconstruct( jan );

        assertThat( deconstruct ).isEqualTo( sData );
        //End Solution::replacewith::fail( "method StudentMapperDeconstructs completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    public void tMapperStreams() {
        Stream<FieldPair> stream = mapper.stream( jan );
        assertThat( stream ).contains( fp( "lastname", "Klaassen" ), fp(
                "cohort", 2018 ) );
//        fail( "method MapperStreams completed succesfully; you know what to do" );
    }

    // helper
    private FieldPair fp( String fName, Object fValue ) {
        return new FieldPair( fName, fValue );
    }

    //@Disabled("Think TDD")
    @Test
    public void tgetMapper() {
        //Start Solution::replacewith:://TODO check cache caches
        var mapper2 = Mapper.mapperFor( Student.class );

        assertThat( mapper2 ).isNotNull().isSameAs( mapper );
        //End Solution::replacewith::fail( "method getMapper completed succesfully; you know what to do" );
    }

    /**
     * Check stream contents. It is sufficient to test for fieldpair with non
     * primitives, e.g. lastname and one with primitive, e.g. cohort.
     */
    //@Disabled("Think TDD")
    @Test
    void tStream() {
        //Start Solution::replacewith:://TODO test stream
        Stream<FieldPair> stream = mapper.stream( jan );
        assertThat( stream ).contains( fp( "lastname", "Klaassen" ), fp(
                "cohort", 2018 ) );
        //End Solution::replacewith::fail( "method tStream completed succesfully; you know what to do" );
    }

    @Test
    public void tUnknownClassShouldThrow() {
        ThrowingCallable code = () -> {
            var mapper1 = Mapper.mapperFor( Course.class );
        };

        assertThatThrownBy( code )
                .isInstanceOf( RuntimeException.class );
    }

    //@Disabled("Think TDD")
    @Test
    void tExtractorTest() {
        assertThat( mapper.keyExtractor().apply( jan ) ).isEqualTo( snummer );
//        fail( "method ExtractorTest completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tLoadTutor() {

        assertThatCode( () -> {
            Mapper<Tutor, Object> tutorMapper = Mapper.mapperFor(
                    testentities.Tutor.class );
            assertThat( tutorMapper.keyType() ).isSameAs( Integer.class );
        } ).doesNotThrowAnyException();
//        fail( "method LoadTutor completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tLoadEntityFields() {
        assertThatCode( () -> {
            Mapper<Tutor, Object> tutorMapper = Mapper.mapperFor(
                    testentities.Tutor.class );
            assertThat( tutorMapper.entityFields() ).hasSize( 9 );
        } ).doesNotThrowAnyException();
//        fail( "method LoadEnityFields completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tReplaceFields() {
        Student piet = new Student( 123432, "Puk", null, "Piet",
                of( 1977, 6, 7 ), 1999,
                "p.puk@student.fantys.nl", "M", "ALUMNI",
                Boolean.FALSE );

        Mapper<Student, Integer> sm = Mapper
                .mapperFor( Student.class );
        final Student pieterNel = sm.replaceFields( piet, fp(
                "gender", "F" ),
                fp( "firstname",
                        "Pieternel" ) );

        assertSoftly( s -> {
            s.assertThat( pieterNel.getFirstname() ).isEqualTo( "Pieternel" );
            s.assertThat( pieterNel.getGender() ).isEqualTo( "F" );
        } );
//        fail( "method ReplaceFields completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tGeneratedFieldNames() {
        Mapper<Student, Integer> sm = Mapper.mapperFor( Student.class );
        assertThat( sm.generatedFieldNames() ).contains( "snummer" );
//        fail( "method GeneratedNames completed succesfully; you know what to do" );
    }
    //@Disabled("Think TDD")

    @Test
    void tKeyFieldName() {
        Mapper<Student, Integer> sm = Mapper.mapperFor( Student.class );
        assertThat( sm.getKeyFieldName() ).isEqualTo( "snummer" );
//        fail( "method KeyFieldName completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tGetEntityType() {
        Mapper<Student, Integer> sm = Mapper.mapperFor( Student.class );
        assertThat( sm.getEntityType() ).isSameAs( Student.class );

//        fail( "method GetEntityType completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tArraySize() {
        Mapper<Student, Integer> sm = Mapper.mapperFor( Student.class );
        assertThat( sm.getArraySize() ).isEqualTo( 10 );
//        fail( "method ArraySize completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tStreamNonGenerated() {
        Mapper<Student, Integer> sm = Mapper.mapperFor( Student.class );
        Student piet = new Student( 123432, "Puk", null, "Piet",
                of( 1977, 6, 7 ), 1999,
                "p.puk@student.fantys.nl", "M", "ALUMNI",
                Boolean.FALSE );
        long count = sm.streamNonGenerated( piet ).count();
        assertThat( count ).isEqualTo( 9 );
//        fail( "method StreamNonGenerated completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tNonGeneratedFieldPairs() {
        Mapper<Student, Integer> sm = Mapper.mapperFor( Student.class );
        Student piet = new Student( 123432, "Puk", null, "Piet",
                of( 1977, 6, 7 ), 1999,
                "p.puk@student.fantys.nl", "M", "ALUMNI",
                Boolean.FALSE );
        int count = sm.nonGeneratedFieldPairs( piet ).size();
        assertThat( count ).isEqualTo( 9 );
//        fail( "method NonGeneratedFieldPairs completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tTransientFields() {
        Mapper<Tutor, Integer> tm = Mapper.mapperFor( Tutor.class );

        List<Field> transientFields = tm.transientFields();
        assertThat( transientFields ).extracting( Field::getName ).contains(
                "studentsToCoach" );
//        fail( "method TransientFields completed succesfully; you know what to do" );
    }

    //@Disabled("think TDD")
    @Test
    public void tGetTypedField() {
        Mapper<Tutor, Integer> tm = Mapper.mapperFor( Tutor.class );
        List<String> fieldsOfType = tm.getFieldsOfType( String.class );
        assertThat( fieldsOfType ).contains( "lastname", "firstname" );

//        fail( "method tGetTypedField reached end. You know what to do." );
    }
}
