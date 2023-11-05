package generatedmappers;

import testentities.Person;
import genericmapper.Mapper;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class PersonMapper extends Mapper<Person, String> {

    // No public ctor 
    private PersonMapper() {
        super( Person.class );
    }

    // self register
    static {
        Mapper.register( new PersonMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(  Person p ) {
           return new Object[]{
                            p.getFirstname(),
              p.getLastname(),
              p.getTussenvoegsel(),
              p.getDob(),
              p.getGender()
           }; 
    }

    @Override
    public Function<Person, String> keyExtractor() {
        return ( Person p ) -> p.getFirstname();
    }

    @Override
    public Class<String> keyType() {
        return String.class;

    }
}

