package generatedmappers;

import testentities.Door;
import genericmapper.Mapper;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class DoorMapper extends Mapper<Door, String> {

    // No public ctor 
    private DoorMapper() {
        super( Door.class );
    }

    // self register
    static {
        Mapper.register( new DoorMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(  Door d ) {
           return new Object[]{
                            d.getDoorName(),
              d.getColor()
           }; 
    }

    @Override
    public Function<Door, String> keyExtractor() {
        return ( Door d ) -> d.getDoorName();
    }

    @Override
    public Class<String> keyType() {
        return String.class;

    }
}

