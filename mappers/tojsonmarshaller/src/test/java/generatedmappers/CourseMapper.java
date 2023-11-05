package generatedmappers;

import testentities.Course;
import genericmapper.Mapper;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class CourseMapper extends Mapper<Course, Integer> {

    // No public ctor 
    private CourseMapper() {
        super( Course.class );
    }

    // self register
    static {
        Mapper.register( new CourseMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(  Course c ) {
           return new Object[]{
                            c.getCourseId(),
              c.getCourseName(),
              c.getCredits(),
              c.getDescription(),
              c.getSemester()
           }; 
    }

    @Override
    public Function<Course, Integer> keyExtractor() {
        return ( Course c ) -> c.getCourseId();
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}

