package generatedmappers;

import testentities.Engine;
import genericmapper.Mapper;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class EngineMapper extends Mapper<Engine, String> {

    // No public ctor 
    private EngineMapper() {
        super( Engine.class );
    }

    // self register
    static {
        Mapper.register( new EngineMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(  Engine e ) {
           return new Object[]{
                            e.getType(),
              e.getCilinders(),
              e.getHp()
           }; 
    }

    @Override
    public Function<Engine, String> keyExtractor() {
        return ( Engine e ) -> e.getType();
    }

    @Override
    public Class<String> keyType() {
        return String.class;

    }
}

