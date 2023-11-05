package testentities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandehombergh@gmail.com}
 */
public class Tutor extends Person {

    private static final long serialVersionUID = 1L;

//    @ID
    private final Integer id;
    private final String academicTitle;
    private final String teaches;
    private final String email;
    private final transient List<Student> studentsToCoach = new ArrayList<>();
    Tutor( String firstname,
           String lastname,
           String tussenvoegsel,
           LocalDate dob,
           String gender,
           Integer id,
           String academicTitle,
           String teaches,
           String email
    ) {
        super(
              firstname, 
                lastname,
              tussenvoegsel, 
              dob, 
              gender 
        );
        this.academicTitle=academicTitle;
        this.id=id;
        this.email=email;
        this.teaches=teaches;
        
    }

    public Tutor(
            String firstname,
            String lastname,
            String tussenvoegsel,
            LocalDate dob,
            String gender,
            int employeeNumber,
            String academicTitle,
            String teaches,
            String email ) {
        super( firstname, lastname, tussenvoegsel, dob, gender );
        this.academicTitle = academicTitle;
        this.teaches = teaches;
        this.email = email;
        this.id = employeeNumber;
    }

    public Tutor( int employeNumber,
                  String academicTitle,
                  String teaches,
                  String email,
                  String lastname,
                  String tussenvoegsel,
                  String firstname,
                  LocalDate dob,
                  String gender ) {
        super( lastname, tussenvoegsel, firstname, dob, gender );
        this.id = employeNumber;
        this.academicTitle = academicTitle;
        this.teaches = teaches;
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Tutor other = (Tutor) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return super.toString() + "=Tutor{" + "academicTitle=" + academicTitle
                + ", teaches=" + teaches + ", email=" + email + '}';
    }

    public Integer getId() {
        return id;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public String getTeaches() {
        return teaches;
    }

    public String getEmail() {
        return email;
    }

    public List<Student> getStudentsToCoach() {
        return studentsToCoach;
    }

}
