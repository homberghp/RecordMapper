package generatedmappers;

import genericmapper.Mapper;
import testentities.Tutor;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class TutorMapper extends Mapper<Tutor, Integer> {

    // No public ctor 
    private TutorMapper() {
        super( Tutor.class );
    }

    // self register
    static {
        Mapper.register(new TutorMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct( Tutor t ) {
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
    public Function<? super Tutor, ? extends Integer> keyExtractor() {
        return t -> t.getId();
    }

    @Override
    public Class<?> keyType() {
        return Integer.class;
    }

}
