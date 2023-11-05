package entities;

import usertypes.Email;
import static usertypes.Email.email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import nl.fontys.sebivenlo.sebiannotations.Generated;
import nl.fontys.sebivenlo.sebiannotations.ID;

/**
 * Dutch naming. (fields all lower case.)
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @ID
    private Integer employeeid;
    private String lastname;
    private String firstname;
    private Email email;
    private Integer departmentid;
    private Boolean available;
    private LocalDate dob;
    @Generated
    private LocalDate hiredate;

    public Employee( Integer employeeid, String lastname, String firstname,
            Email email, Integer departmentid, Boolean available, LocalDate dob, LocalDate hiredate ) {
        this.employeeid = employeeid;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.departmentid = departmentid;
        this.available = available;
        this.dob = dob;
        this.hiredate = hiredate;
    }

    public Employee( Integer employeeid ) {
        this.employeeid = employeeid;
    }

    public void setEmployeeid( int id ) {
        this.employeeid = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname( String lastname ) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname( String firstname ) {
        this.firstname = firstname;
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.employeeid );
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
        final Employee other = (Employee) obj;
        return ( this.employeeid == null && other.employeeid == null )
                || this.employeeid.equals( other.employeeid );
    }

    @Override
    public String toString() {
        return "Employee{"
                + "employeeid=" + employeeid
                + ", lastname=" + lastname
                + ", firstname=" + firstname
                + ", email=" + email
                + ", departmentid=" + departmentid
                + ", available=" + available
                + ", dob=" + dob
                + '}';
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid( int departmentid ) {
        this.departmentid = departmentid;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail( Email email ) {
        this.email = email;
    }

    public Boolean getAvailable() {
        return available;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob( LocalDate dob ) {
        this.dob = dob;
    }

    public void setEmployeeid( Integer employeeid ) {
        this.employeeid = employeeid;
    }

    public void setAvailable( Boolean available ) {
        this.available = available;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

}
