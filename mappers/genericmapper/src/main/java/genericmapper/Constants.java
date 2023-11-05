package genericmapper;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Constant String values used in Mapper api.
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public enum Constants {
    ;// no values

   
    public static final String GENERATED_PACKAGE = "generatedmappers";
    public static final String DESTINATION_DIRNAME = "src/main/java";

    public static String mapperName(Class<?> entityType) {
        return entityType.getName() + "Mapper";
    }

    private static String templateText(String templateName) {
        String text = "";
        Class clz = Constants.class;

        try (  InputStream in = clz.getResourceAsStream( templateName ) ) {
            text = new String( in.readAllBytes​() );
        } catch ( IOException ex ) {
            Logger.getLogger( Constants.class.getName() )
                    .log( Level.SEVERE, ex.getMessage() );
        }
        return text;
    }

    /**
     * The filename of the template file.
     */
    public static String MAPPER_TEMPLATE = templateText(
            "CodeTemplate-java.txt" );

    public static String generatedJavaFileName(String outDir, Class<?> type) {
        String n = mapperTypeName( type ).replaceAll( "\\.", System.getProperty(
                "file.separator" ) );
        var x = outDir + "/" + n + ".java";
//        System.out.println( "x = " + x );
        return x;
    }

    /**
     * Compute the type name for a mapper, derived from the type to be mapped.
     *
     * @param type to map
     *
     * @return mapper name
     */
    public static String mapperTypeName(Class<?> type) {
        return type.getName() + "Mapper";
    }

    static String beanStyle(String s) {
        return "get" + s.substring( 0, 1 ).toUpperCase() + s
                .substring( 1 );
    }

    /**
     * Determine the naming strategy for getters by reading a the property value
     * "genericmapper.getternamestyle".
     *
     * @return the strategy as string to string function.
     */
    public static Function<Field, String> getterNameStrategy() {
        String strat = System.getProperty( "genericmapper.getternamestyle",
                "BEAN" );
        return GetterNamingStrategy.valueOf( strat.toUpperCase() );

    }

    /**
     * Compute the name for a getter. It uses a strategy, so can easily switch
     * to record types if required.
     *
     * @param f field
     *
     * @return the name , e.g. getName for field name.
     */
    public static String getterName(Field f) {
        return getterNameStrategy().apply( f );
    }

    /**
     * Compute the name for a getter. It uses a strategy, so can easily switch to
     * record types if required.
     *
     * @param namingStrategy either BEAN (default) or RECORD
     * @param f field
     *
     * @return the name , e.g. getName for field name.
     */
    public static String getterName(String namingStrategy, Field f) {
        return GetterNamingStrategy.valueOf( namingStrategy.toUpperCase() ).apply( f );
    }

    /**
     * Helper to check for non normal fields.
     *
     * @param f field to test
     *
     * @return true if not static nor synthetic.
     */
    public static boolean isSerializableField(Field f) {
        int modifiers = f.getModifiers();
        return !( f.isSynthetic()
                || Modifier.isStatic( modifiers ) || Modifier.isTransient(
                modifiers ) );
    }

}
