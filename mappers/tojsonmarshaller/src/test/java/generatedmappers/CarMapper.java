package generatedmappers;

import testentities.Car;
import genericmapper.Mapper;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class CarMapper extends Mapper<Car, String> {

    // No public ctor 
    private CarMapper() {
        super( Car.class );
    }

    // self register
    static {
        Mapper.register( new CarMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct(  Car c ) {
           return new Object[]{
                            c.getBrand(),
              c.getColor(),
              c.getEngine(),
              c.getDoors(),
              c.getWheels()
           }; 
    }

    @Override
    public Function<Car, String> keyExtractor() {
        return ( Car c ) -> c.getBrand();
    }

    @Override
    public Class<String> keyType() {
        return String.class;

    }
}

