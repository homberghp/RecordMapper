package testentities;

import testentities.Student;
import genericmapper.Mapper;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class StudentMapper extends Mapper<Student, Integer> {

    // No public ctor 
    private StudentMapper() {
        super( Student.class, java.lang.invoke.MethodHandles.lookup()  );
    }

    // self register
    static {
        Mapper.register( new StudentMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(  Student s ) {
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
    public Function<Student, Integer> keyExtractor() {
        return ( Student s ) -> s.getSnummer();
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }

    @Override
    public Student construct( Object[] args ) {
          return new Student(
            (Integer) args[0],
            (String) args[1],
            (String) args[2],
            (String) args[3],
            (java.time.LocalDate) args[4],
            (int) args[5],
            (String) args[6],
            (String) args[7],
            (String) args[8],
            (Boolean) args[9]);
    }
}
