package testentities;

import java.time.LocalDate;
import nl.fontys.sebivenlo.sebiannotations.ID;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandehombergh@gmail.com}
 */
public class Tutor extends Person {

    @ID
    private final Integer employeeNumber;
    private final String academicTitle;
    private final String teaches;
    private final String email;

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
        this.employeeNumber = employeeNumber;
    }

    public Tutor( int employeNumber, String academicTitle, String teaches,
            String email, String lastname, String tussenvoegsel,
            String firstname, LocalDate dob, String gender ) {
        super( lastname, tussenvoegsel, firstname, dob, gender );
        this.employeeNumber = employeNumber;
        this.academicTitle = academicTitle;
        this.teaches = teaches;
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.employeeNumber;
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
        if ( this.employeeNumber != other.employeeNumber ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "=Tutor{" + "academicTitle=" + academicTitle
                + ", teaches=" + teaches + ", email=" + email + '}';
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
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

    
}
