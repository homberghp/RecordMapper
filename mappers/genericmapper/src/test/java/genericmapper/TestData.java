package genericmapper;

import java.time.LocalDate;
import static java.time.LocalDate.of;
import testentities.Course;
import testentities.Student;
import testentities.Tutor;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmial.com}
 */
public class TestData {

    static final Integer snummer = 123;
    static final String studentLastName = "Klaassen";
    static final String studentTussenVoegsel = null;
    static final String studentFirstname = "Jan";
    static final LocalDate studentDob = of( 2001, 10, 07 );
    static final int cohort = 2018;
    static final String studentEmail = "jan.klaassen@student.fantys.nl";
    static final String studentGender = "M";
    static final String group = "INF-ABC";
    static final Boolean active = true;

    static String[] studentFieldNames = {
        "snummer",
        "lastname",
        "tussenvoegsel",
        "firstname",
        "dob",
        "cohort",
        "email",
        "gender",
        "student_class",
        "active"

    };

    static Student jan = new Student(
            snummer, studentLastName, studentTussenVoegsel, studentFirstname,
            studentDob, cohort, studentEmail,
            studentGender, group, true
    );

    static Student jan2 = new Student(
            snummer, studentLastName, studentTussenVoegsel, studentFirstname,
            studentDob, cohort, studentEmail,
            studentGender, group, true
    );

    static Object[] sData = {
        snummer, studentLastName, studentTussenVoegsel, studentFirstname,
        studentDob, cohort, studentEmail, studentGender,
        group, active
    };

    static final int courseId = 1234;
    static final String courseName = "PRC2";
    static final int credits = 5;
    static final String description = "Programing concepts 2 using Java ";
    static final short semester = 2;

    static String[] courseFieldNames = {
        "courseId",
        "courseName",
        "credits",
        "description",
        "semester"

    };

    static String[] tutorFieldNames = {
        "lastname",
        "tussenvoegsel",
        "firstname",
        "dob",
        "gender",
        "employeeNumber",
        "academicTitle",
        "email",
        "teaches"

    };

    static Object[] prc2Data = {
        courseId, courseName, credits, description, semester
    };

    static Course prc2 = new Course( courseId, courseName, credits, description,
            semester );

    static final String tutorLastname = "Janzen";
    static final String tutorTussenvoegsel = null;
    static final String tutorFirstname = "Suzan";
    static final LocalDate tutorDob = LocalDate.of( 1991, 12, 3 );
    static final String tutorGender = "F";
    static final Integer tutorEmployNumber = 4711;
    static final String tutorAcademicTitle = "MSc";
    static final String tutorEmail = "Susan.Janzen@fantys.nl";
    static final String teaches = "PRJ2|PRC2";

    static final Tutor suzan = new Tutor(
            tutorFirstname,
            tutorLastname,
            tutorTussenvoegsel,
            tutorDob,
            tutorGender,
            tutorEmployNumber,
            tutorAcademicTitle,
            tutorEmail,
            teaches );
    static final Object[] suzanData = {
        tutorLastname,
        tutorTussenvoegsel,
        tutorFirstname,
        tutorDob,
        tutorGender,
        tutorEmployNumber,
        tutorAcademicTitle,
        tutorEmail,
        teaches
    };
}
