package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import nl.fontys.sebivenlo.sebiannotations.ID;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class Person implements Serializable {

    @ID
    private final String firstname;
    private final String lastname;
    private final String tussenvoegsel;
    private final LocalDate dob;
    private final String gender;

    public Person( String lastname, String tussenvoegsel, String firstname,
            LocalDate dob, String gender ) {
        this.lastname = lastname;
        this.tussenvoegsel = tussenvoegsel;
        this.firstname = firstname;
        this.dob = dob;
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode( this.lastname );
        hash = 53 * hash + Objects.hashCode( this.tussenvoegsel );
        hash = 53 * hash + Objects.hashCode( this.firstname );
        hash = 53 * hash + Objects.hashCode( this.dob );
        hash = 53 * hash + Objects.hashCode( this.gender );
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
        final Person other = (Person) obj;
        if ( !Objects.equals( this.lastname, other.lastname ) ) {
            return false;
        }
        if ( !Objects.equals( this.tussenvoegsel, other.tussenvoegsel ) ) {
            return false;
        }
        if ( !Objects.equals( this.firstname, other.firstname ) ) {
            return false;
        }
        if ( !Objects.equals( this.gender, other.gender ) ) {
            return false;
        }
        if ( !Objects.equals( this.dob, other.dob ) ) {
            return false;
        }
        return true;

    }

    @Override
    public String toString() {
        return "Person{" + "lastname=" + lastname + ", tussenvoegsel="
                + tussenvoegsel + ", firstname=" + firstname + ", dob=" + dob
                + ", gender=" + gender + '}';
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

    public String getGender() {
        return gender;
    }

}
