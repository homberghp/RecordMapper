package nl.fontys.sebivenlo.sebiannotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify the table name for an entity.
 * 
 * Use it override the default generated name which is
 * {@code class.getSimpleName().toLowerCase() + "s"}.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
public @interface TableName {

    /**
     * Get the name of the table.
     * @return the name.
     */
    String value() default "mytable";
}
