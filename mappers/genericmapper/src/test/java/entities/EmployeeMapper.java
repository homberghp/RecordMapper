package entities;

import genericmapper.Mapper;
import java.util.function.Function;

/**
 * Generated code. Do not edit, your changes will be lost.
 */
public class EmployeeMapper extends Mapper<Employee, Integer> {

    // No public ctor 
    private EmployeeMapper() {
        super( Employee.class, java.lang.invoke.MethodHandles.lookup() );
    }

    // self register
    static {
        Mapper.register( new EmployeeMapper() );
    }

    // the method that it is all about
    @Override
    public Object[] deconstruct( Employee e ) {
        return new Object[]{
            e.getEmployeeid(),
            e.getLastname(),
            e.getFirstname(),
            e.getEmail(),
            e.getDepartmentid(),
            e.getAvailable(),
            e.getDob(),
            
        };
    }

    @Override
    public Function<Employee, Integer> keyExtractor() {
        return ( Employee e ) -> e.getEmployeeid();
    }

    @Override
    public Class<Integer> keyType() {
        return Integer.class;

    }
}
