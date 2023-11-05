package genericmapper;

import static genericmapper.Constants.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.joining;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import nl.fontys.sebivenlo.sebiannotations.ID;

/**
 * Generates mappers for named Types. The types are fully qualified types to be
 * read from the class path.
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class MapperGenerator {

    final Class<?> entityType;
    final Field[] allFields;

    public MapperGenerator( Class<?> entitype ) {
        this.entityType = entitype;
        this.allFields = getAllFieldsInClassHierarchy();
    }

    /**
     * Generate the java code using the template MAPPER_TEMPLATE.
     *
     * @return the template text or an empty string when the class has no usable
     * id field.
     */
    public final String javaSource() {
        String classText = "";
        //Start Solution::replacewith:://TODO generate the code

        try {
            String simpleTypeName = entityType.getSimpleName();
            String typeName = entityType.getName();
            String packageName = entityType.getPackageName();
            Field keyField = getKeyField();
            String keyGetter = getterName( keyField );
            classText = String.format( MAPPER_TEMPLATE,
                    packageName, // 1
                    typeName, //2 for import
                    simpleTypeName, //3
                    keyField.getType().getSimpleName(), // 4
                    Character.toLowerCase( simpleTypeName
                            .charAt( 0 ) ), //5
                    getters(), //6
                    keyGetter, // 7
                    construct(), //8
                    LocalDateTime.now()
            );
        } catch ( NoSuchFieldError nsf ) {
            Logger.getLogger( getClass().getName() ).log( Level.INFO, nsf
                    .getMessage() );
        }

        return classText;
        //End Solution::replacewith::return "";
    }

    /**
     * Turn the fields of a class into getter call strings.
     *
     * @param type to reflect
     *
     * @return The getters as one indented string.
     */
    final String getters() {
        //Start Solution::replacewith:://TODO generate the getter calls.
        String typeName = entityType.getSimpleName();
        String paramName = typeName.substring( 0, 1 ).toLowerCase();
        String paramNameDot = paramName + ".";
        String indent = "              ";
        return Stream.of( allFields ) //.map( this::getterName).
                .map( f -> Constants.getterName( f )  )
                .map( s -> indent + paramNameDot + s )
                .collect( joining( ",\n" ) );
        //End Solution::replacewith::return "";
    }

    /**
     * Generate the array of fields in top down declaration order. Top down
     * means the super stuff first.
     *
     * @return the array of all declared fields in the class hierarchy.
     */
    final Field[] getAllFieldsInClassHierarchy() {
        return Mapper.allFields( entityType ).filter(f-> Constants.isSerializableField( f )).toArray( Field[]::new );
    }

    /**
     * Try to find the Annotation @ID and if that fails the field called "id".
     *
     * New strategy. annotation, field named id, field named entitynameid,
     *
     * @return the field.
     *
     * @throws NoSuchFieldError after two attempts
     */
    Field getKeyField() {
        return Stream.of( entityType.getDeclaredFields() )
                //                .peek( f -> { System.out.println(f);} )
                .filter( f -> f.getAnnotation( ID.class ) != null )
                .findFirst()
                .or( this::getFieldNamedId )
                .or( this::getFieldNamedEntityId )
                //                .orElse( null );
                .orElseThrow( () -> new NoSuchFieldError(
                "Can't infer id-field for class " + entityType.getName() ) );
    }

    Optional<Field> getFieldNamedId() {
        return Stream.of( entityType.getDeclaredFields() ).filter(
                f -> "id".equalsIgnoreCase( f.getName() ) )
                .findFirst();
    }

    Optional<Field> getFieldNamedEntityId() {
        return Stream.of( entityType.getDeclaredFields() ).filter(
                f -> ( entityType.getSimpleName().toLowerCase() + "id" )
                        .equalsIgnoreCase( f.getName() ) )
                .findFirst();
    }

    static String castExpression(Class<?> t) {
        if ( !t.isArray() ) {
            return '('+ (t.getPackageName().equals( "java.lang" ) ? t.getSimpleName() : t.getName())+')';
        }

        int n = 0;
        while ( t.isArray() ) {
            n++;
            t = t.getComponentType();
        }
        return '('+( t.getPackageName().equals( "java.lang" ) ? t.getSimpleName() : t.getName() ) + "[]".repeat( n )+')';
    }

    String construct() {
        String casts = IntStream.range( 0, this.allFields.length )
                .mapToObj(idx -> String.format("%s args[%d]", castExpression( allFields[ idx ].getType() ), idx ) )
                .collect( joining( ",\n            " ) );

        return "    public " + entityType.getSimpleName() + " construct( Object[] args ) {"
                + "\n"
                + "          return new " + entityType.getSimpleName()
                + "(\n            "
                + casts + ""
                + ");\n    }";
    }
}
