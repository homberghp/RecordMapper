package tojsonmarshaller;

import genericmapper.FieldPair;
import genericmapper.Mapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

/**
 *
 * @author "Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}"
 * @param <E> entity type to be converted.
 */
public class ToJsonMarshaller<E> {

    /**
     * Set of types that are considered atomic, so that an (optionally quoted)
     * toString suffices.
     */
    static final Set<Class<?>> atomSet = Set.of(
            String.class,
            Integer.class,
            int.class,
            Double.class,
            double.class,
            Long.class,
            long.class,
            Boolean.class,
            boolean.class,
            Short.class,
            short.class,
            Float.class,
            float.class,
            LocalDate.class,
            LocalDateTime.class
    );

    final Class<E> entityType;
    final Mapper<E,Object> mapper;

    private ToJsonMarshaller( Class<E> entityType ) {
        this.entityType = entityType;
        mapper = Mapper.mapperFor( entityType );
    }

    public static ToJsonMarshaller forEntity( Object e ) {
        return forType( e.getClass() );
    }

    private static final ConcurrentMap<Class<?>, ToJsonMarshaller<?>> cache = new ConcurrentHashMap<>();

    public static ToJsonMarshaller forType( Class<?> clz ) {
        return cache
                .computeIfAbsent( clz, ( c ) -> new ToJsonMarshaller<>( c ) );
    }

    static String pairToString( String key, Object value ) {
        String quotedValue;
        if ( value == null ) {
            quotedValue = "null";
        } else if ( ( value instanceof Number ) || ( value instanceof Boolean ) ) {
            quotedValue = value.toString();
        } else {
            quotedValue = '"' + value.toString() + '"';
        }
        return '"' + key + "\":" + quotedValue;
    }

    private static String pairToString( FieldPair fp ) {
        Object value = fp.value();
        String valueString;
        if ( atomSet.contains( value.getClass() ) ) {
            return pairToString( fp.key(), fp.value() );
        } else {
            valueString = ToJsonMarshaller.asJson( value );
            return "\"" + fp.key() + "\":" + valueString;
        }
    }

    public static String asJson( Object entity ) {
        if ( entity == null ) {
            return "null";
        }
        if ( entity instanceof String ) {
            return "\"" + String.class.cast( entity ) + "\"";
        }
        Class<?> entityType = entity.getClass();
        if ( atomSet.contains( entityType ) ) {
            return atomToString( entity );
        }

        if ( !entityType.isArray() && !( entity instanceof Collection ) ) {

            return "{\n" + ToJsonMarshaller.forType( entityType )
                    .simpleToString(
                            entity )
                    + "\n}";
        }

        if ( entityType.isArray() ) {
            Class<?> componentType = entityType.getComponentType();
            if ( componentType.isPrimitive() ) {
                return primitiveArrayString( componentType, entity );
            }
            Object[] array = Object[].class.cast( entity );
            var stream = Stream.of( array );
            return streamToJSon( stream );
        }

        Collection<?> collection = (Collection<?>) entity;
        if ( collection.isEmpty() ) {
            return "[]";
        }
        var stream = collection.stream();
        return streamToJSon( stream );
    }

    private static String streamToJSon(
            Stream<?> stream ) {
        return "[\n" + stream.map( e -> ToJsonMarshaller.asJson( e ) )
                .collect( joining( ",\n" ) ) + "\n]";
    }

    final String simpleToString( E entity ) {
        return mapper.stream( entity )
                .filter( fp -> !fp.hasNullValue() )
                .map( ToJsonMarshaller::pairToString )
                .collect( joining( ",\n" ) );
    }

    static String atomToString( Object value ) {
        if ( value == null ) {
            return "null";
        }
        if ( ( value instanceof Number ) || ( value instanceof Boolean ) ) {
            return value.toString();
        } else {
            return '"' + value.toString() + '"';
        }
    }

    private static final Map<Class<?>, Function<? super Object, String>> primArrayMapper
            = Map.of(
                    int.class, ( e ) -> Arrays.toString( (int[]) e ),
                    long.class, ( e ) -> Arrays.toString( (long[]) e ),
                    float.class, ( e ) -> Arrays.toString( (float[]) e ),
                    double.class, ( e ) -> Arrays.toString( (double[]) e ),
                    short.class, ( e ) -> Arrays.toString( (short[]) e ),
                    byte.class, ( e ) -> Arrays.toString( (byte[]) e ),
                    char.class, ( e ) -> Arrays.toString( (char[]) e ),
                    boolean.class, ( e ) -> Arrays.toString( (boolean[]) e )
            );

    private static String primitiveArrayString( Class<?> elemenType,
            Object entity ) {
        return primArrayMapper.get( elemenType ).apply( entity );
    }
}
