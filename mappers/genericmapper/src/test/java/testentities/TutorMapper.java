package testentities;

import testentities.Tutor;
import genericmapper.Mapper;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class TutorMapper extends Mapper<Tutor, Integer> {

    // No public ctor 
    private TutorMapper() {
        super( Tutor.class, java.lang.invoke.MethodHandles.lookup()  );
    }

    // self register
    static {
        Mapper.register( new TutorMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(  Tutor t ) {
           return new Object[]{
              t.getFirstname(),
              t.getLastname(),
              t.getTussenvoegsel(),
              t.getDob(),
              t.getGender(),
              t.getId(),
              t.getAcademicTitle(),
              t.getTeaches(),
              t.getEmail()
           }; 
    }

    @Override
    public Function<Tutor, Integer> keyExtractor() {
        return ( Tutor t ) -> t.getId();
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}
