package genericmapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import testentities.Student;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
//@Disabled
@TestMethodOrder( MethodName.class )
public class MapperGeneratorTest {

    @BeforeAll
    static void cleanup() throws IOException {
        File out = new File( "out" );
        if ( out.exists() && out.isDirectory() ) {
            Files.walk( Path.of( "out" ) )
                    .sorted( Comparator.reverseOrder() )
                    .map( Path::toFile )
                    .peek( System.out::println )
                    .forEach( File::delete );
        }
    }

    /**
     * Helper method to clean code of unneeded whitespace and empty lines. This
     * methods prints the javaCode to standard output on first encounter.
     *
     * @param javaCode to process
     * @return list of clean lines
     */
    static List<String> cleanCode( String javaCode ) {
        List<String> cleanCode
                = Stream.of( javaCode.split( "\n" ) )
                        .map( String::trim )
                        .filter( s -> !s.isEmpty() )
                        .collect( toList() );
        // test if already seen.
        if ( !shownCode.contains( javaCode ) ) {
            System.out.println( "<pre>" );
            // a captured object must be final, but may be muttable
            AtomicInteger counter = new AtomicInteger();
            cleanCode.forEach( l -> System.out.println( counter.getAndIncrement()
                    + " " + l ) );
            System.out.println( "</pre>" );
            shownCode.add( javaCode );
        }
        return cleanCode;
    }

    private static Set<String> shownCode = new HashSet<>();

    // to abbreviate new String[]{...} to s(...) 
    static String[] s( String... s ) {
        return s;
    }

    static Stream<Arguments> testData2() {
        return Stream.of(
                arguments( 0, s( "package", "testentities;" ) ),
                arguments( 1, s( "import", "testentities.Student;" ) ),
                arguments( 2, s( "import", "genericmapper.Mapper;" ) ),
                arguments( 3, s( "import", "java.util.function.Function;" ) ),
                arguments( 7,
                        s( "public", "class", "StudentMapper", "extends",
                                "Mapper<Student,", "Integer>", "{" ) ),
                arguments( 9, s( "private", "StudentMapper()", "{" ) ),
                arguments( 10, s( "super(", "Student.class,",
                        "java.lang.invoke.MethodHandles.lookup()", ");" ) ),
                arguments( 13, s( "static", "{" ) ),
                arguments( 14, s( "Mapper.register(", "new", "StudentMapper()",
                        ");" ) ),
                arguments( 15, s( "}" ) ),
                arguments( 17, s( "@Override" ) ),
                arguments( 18,
                        s( "public", "Object[]", "deconstruct(", "Student", "s" ) ),
                arguments( 19, s( "return", "new", "Object[]{" ) ),
                arguments( 32, s( "@Override" ) ),
                arguments( 33, s( "public", "Function<Student,", "Integer>",
                        "keyExtractor()" ) ),
                arguments( 34, s( "return", "(", "Student", "s", "->",
                        "s.getSnummer();" ) ),
                arguments( 36, s( "@Override" ) ),
                arguments( 37, s( "public", "Class<Integer>", "keyType()", "{" ) ),
                arguments( 38, s( "return", "Integer.class;" ) ),
                arguments( 40, s( "@Override" ) ),
                arguments( 41, s( "public", "Student", "construct(", "Object[]", "args", ")", "{" ) ),
                arguments( 42, s( "return", "new", "Student(" ) ),
                arguments( 43, s( "(Integer)", "args[0]," ) ),
                arguments( 44, s( "(String)", "args[1]," ) ),
                arguments( 45, s( "(String)", "args[2]," ) ),
                arguments( 46, s( "(String)", "args[3]," ) ),
                arguments( 47, s( "(java.time.LocalDate)", "args[4]," ) ),
                arguments( 48, s( "(int)", "args[5]," ) ),
                arguments( 49, s( "(String)", "args[6]," ) ),
                arguments( 50, s( "(String)", "args[7]," ) ),
                arguments( 51, s( "(String)", "args[8]," ) ),
                arguments( 52, s( "(Boolean)", "args[9]);" ) ),
                arguments( 53, s( "}" ) )
        );
    }
    Class<?> type = Student.class;

    /**
     * Test the preamble of the deconstructor:
     * <ol>
     * <li> package declaration</li>
     * <li> import</li>
     * <li> private constructor</li>
     * <li> static block</li>
     * <li> Register call</li>
     * <li> curly</li>
     * <li> @Override</li>
     * <li>method signature</li>
     * <li>return statement</li>
     * </ol>
     *
     * @param line number in cleaned code, see data above
     * @param elements
     */
    //@Disabled("Think TDD")
    @ParameterizedTest
    @MethodSource( "testData2" )
    void t0CodeLine( int line, String[] elements ) {
        var gen = new MapperGenerator( type );
        List<String> javaCode = cleanCode( gen.javaSource() );
        String[] words = javaCode.get( line ).split( "\\s+" );
        //Start Solution::replacewith:://TODO assert correctness
        assertThat( words ).as( "expected on line " + line )
                .contains( elements );
        //End Solution::replacewith::fail("t0CodeLine completed succesfully; you know what to do");
    }

    /**
     * Get the getters, split into list, and check that the expected number of
     * getter is there. Test with class student. The generated code should have
     * the correct number of lines with respect to the number of fields in the
     * type.
     */
    //@Disabled("Think TDD")
    @Test
    void t1Getters() {
        Class<?> type = Student.class;
        var gen = new MapperGenerator( type );
        List<String> javaCode = cleanCode( gen.getters() );
        int fieldCount = gen.getAllFieldsInClassHierarchy().length;
        System.out.println( "fieldCount = " + fieldCount );
        //Start Solution::replacewith:://TODO count the lines 
        assertThat( javaCode.size() ).isEqualTo( fieldCount );
        //End Solution::replacewith::fail( "method t3Getters completed succesfully; you know what to do" );
    }

    /**
     * Count the {},(), and [], they should match in count.
     */
    //@Disabled("Think TDD")
    @ParameterizedTest
    @CsvSource( {
        "{,}",
        "[,]",
        "(,)"
    } )
    void t2countBraces( char open, char close ) {
        Class<?> type = Student.class;
        var gen = new MapperGenerator( type );
        String javaText = gen.javaSource();
        long opening = javaText.chars().filter( c -> c == open ).count();
        long closing = javaText.chars().filter( c -> c == close ).count();
        //Start Solution::replacewith:://TODO check that braces match
        assertThat( opening ).as( "counting braces" + open + close ).isEqualTo(
                closing );
        //End Solution::replacewith::fail( "method countCurlies completed succesfully; you know what to do" );
    }

    /**
     * Test that main runs exception free.
     */
    //@Disabled("Think TDD")
    @Test
    void t3MainCausesNoProblems() {
        assertThatCode( () -> {
            MapperGeneratorRunner
                    .main( new String[]{ "testentities" } );
        } ).doesNotThrowAnyException();
//        fail( "method MainCausesNoProblems completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    void tGenerateTutorMapper() {
        assertThatCode( () -> {
            MapperGeneratorRunner
                    .main( new String[]{ "testentities" } );
        } ).doesNotThrowAnyException();
//        fail( "method GenerateTutorMapper completed succesfully; you know what to do" );
    }

    //@Disabled("Think TDD")
    @Test
    public void candidateEntityNames() throws IOException {
        MapperGeneratorRunner runner = new MapperGeneratorRunner( ".", "out", new String[]{ "testentities" } );

        Set<String> canditateEntityNames = runner.getCanditateEntityNames( "target/classes", "entities" );
        assertThat( canditateEntityNames ).doesNotContain( "genericmapper.Constants" );
//        fail( "method candidateEntityNames reached end. You know what to do." );
    }

    //@Disabled("Think TDD")
    @Test
    public void generateConstruct() {
        var gen = new MapperGenerator( type );
        String construct = gen.construct();
        System.out.println( "construct =\n" + construct );
//        fail( "method generateConstruct reached end. You know what to do." );
    }
}
