package client;

import genericmapper.Mapper;
import testentities.Student;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class StudentMapper extends Mapper<Student, Integer> {

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

    @Override
    public Function<? super Student, ? extends Integer> keyExtractor() {
        return s -> s.getSnummer();
    }

    @Override
    public Class<?> keyType() {
        return Integer.class;
    }

}
