package sampleentities;

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

    private final String lastname;
    private final String tussenvoegsel;
    private final String firstname;
    private final LocalDate dob;
    private final int cohort;
    private final String email;
    private final String gender;
    private final String student_class;
    @ID
    private final Integer snummer;
    private final Boolean active;

    public Student(  String lastname, String tussenvoegsel,
            String firstname, LocalDate dob, int cohort, String email,
            String gender, String student_class,Integer snummer, Boolean active ) {
        this.lastname = lastname;
        this.tussenvoegsel = tussenvoegsel;
        this.firstname = firstname;
        this.dob = dob;
        this.cohort = cohort;
        this.email = email;
        this.gender = gender;
        this.student_class = student_class;
        this.snummer = snummer;
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
        hash = 37 * hash + Objects.hashCode( this.snummer );
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
        if ( !Objects.equals( this.snummer, other.snummer ) ) {
            return false;
        }
        return true;
    }
}
