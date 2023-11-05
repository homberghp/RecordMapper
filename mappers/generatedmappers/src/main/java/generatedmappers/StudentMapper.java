package generatedmappers;

import sampleentities.Student;
import genericmapper.Mapper;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class StudentMapper extends Mapper<Student> {

    // No public ctor 
    private StudentMapper() {
        super( Student.class );
    }

    // self register
    static {
        Mapper.register( new StudentMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct( Student s ) {
        return new Object[]{
            s.getSnummer(),
            s.getLastname(),
            s.getTussenvoegsel(),
            s.getFirstname(),
            s.getDob(),
            s.getCohort(),
            s.getEmail(),
            s.getGender(),
            s.getStudent_class(),
            s.getActive()
        };
    }
}
