package tojsonmarshaller;

import testentities.Student;
import java.time.LocalDate;
import static java.time.LocalDate.of;
import java.util.Arrays;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class TestData {

    static Integer snummer = 123;
    static String lastName = "Klaassen";
    static String tussenVoegsel = null;
    static String firstName = "Jan";
    static LocalDate dob = of( 2001, 10, 07 );
    static int cohort=2018;
    static String email = "jan@home.nl";
    static String gender = "M";
    static String group = "INF-ABC";
    static Boolean active = true;
    static Object[] studentArgs = new Object[]{
        snummer, lastName, tussenVoegsel, firstName, dob, cohort, email, gender,
        group, true
    };

    static Student jan = new Student(
            snummer, lastName, tussenVoegsel, firstName, dob, cohort, email,
            gender, group, true
    );

    static Student piet = new Student(
            snummer, "Puk", null, "Piet", dob, cohort, email,
            gender, group, true
    );

    static {
        System.out.println( "jan = " + jan );
        System.out.println(
                "studentArgs = " + Arrays.deepToString( studentArgs ) );
    }
}
