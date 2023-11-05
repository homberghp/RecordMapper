package entities;

import java.util.Objects;

/**
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class Course {

//    @ID
    final Integer courseId;
    final String courseName;
    final int credits;
    final String description;
    final short semester;

    public Course( int courseId, String courseName, int credits,
            String description, short semester ) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.description = description;
        this.semester = semester;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public String getDescription() {
        return description;
    }

    public short getSemester() {
        return semester;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.courseId;
        hash = 83 * hash + Objects.hashCode( this.courseName );
        hash = 83 * hash + this.credits;
        hash = 83 * hash + Objects.hashCode( this.description );
        hash = 83 * hash + this.semester;
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
        final Course other = (Course) obj;
        if ( this.courseId != other.courseId ) {
            return false;
        }
        if ( this.credits != other.credits ) {
            return false;
        }
        if ( this.semester != other.semester ) {
            return false;
        }
        if ( !Objects.equals( this.courseName, other.courseName ) ) {
            return false;
        }
        if ( !Objects.equals( this.description, other.description ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId + ", courseName=" + courseName
                + ", credits=" + credits + ", description=" + description
                + ", semester=" + semester + '}';
    }

}
