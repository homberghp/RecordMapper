package testentities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import nl.fontys.sebivenlo.sebiannotations.ID;

/**
 * Simple student with LocalDate birthday.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class Student implements Serializable {

    @ID
    private final Integer snummer;
    private final String lastname;
    private final String tussenvoegsel;
    private final String firstname;
    private final LocalDate dob;
    private final int cohort;
    private final String email;
    private final String gender;
    private final String student_class;
    private final Boolean active;

    public Student(Integer snummer, String lastname, String tussenvoegsel,
            String firstname, LocalDate dob, int cohort, String email,
            String gender, String student_class, Boolean active) {
        this.snummer = snummer;
        this.lastname = lastname;
        this.tussenvoegsel = tussenvoegsel;
        this.firstname = firstname;
        this.dob = dob;
        this.cohort = cohort;
        this.email = email;
        this.gender = gender;
        this.student_class = student_class;
        this.active = active;
    }

    public Integer getSnummer() {
        return snummer;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getFirstname() {
        return firstname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public int getCohort() {
        return cohort;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStudent_class() {
        return student_class;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode( this.snummer );
        hash = 83 * hash + Objects.hashCode( this.lastname );
        hash = 83 * hash + Objects.hashCode( this.tussenvoegsel );
        hash = 83 * hash + Objects.hashCode( this.firstname );
        hash = 83 * hash + Objects.hashCode( this.dob );
        hash = 83 * hash + this.cohort;
        hash = 83 * hash + Objects.hashCode( this.email );
        hash = 83 * hash + Objects.hashCode( this.gender );
        hash = 83 * hash + Objects.hashCode( this.student_class );
        hash = 83 * hash + Objects.hashCode( this.active );
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
        final Student other = (Student) obj;
        if ( this.cohort != other.cohort ) {
            return false;
        }
        if ( !Objects.equals( this.lastname, other.lastname ) ) {
            return false;
        }
        if ( !Objects.equals( this.tussenvoegsel, other.tussenvoegsel ) ) {
            return false;
        }
        if ( !Objects.equals( this.firstname, other.firstname ) ) {
            return false;
        }
        if ( !Objects.equals( this.email, other.email ) ) {
            return false;
        }
        if ( !Objects.equals( this.gender, other.gender ) ) {
            return false;
        }
        if ( !Objects.equals( this.student_class, other.student_class ) ) {
            return false;
        }
        if ( !Objects.equals( this.snummer, other.snummer ) ) {
            return false;
        }
        if ( !Objects.equals( this.dob, other.dob ) ) {
            return false;
        }
        if ( !Objects.equals( this.active, other.active ) ) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Student{" + "snummer=" + snummer + ", lastname=" + lastname +
                ", tussenvoegsel=" + tussenvoegsel + ", firstname=" + firstname +
                ", dob=" + dob + ", cohort=" + cohort + ", email=" + email +
                ", gender=" + gender + ", student_class=" + student_class +
                ", active=" + active + '}';
    }
    
}
