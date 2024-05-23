package com.qt.annotation.values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * Annotation used to specify the name of a page or a component class.
 * Can be applied to classes.
 */

@Target(ElementType.TYPE) @Retention(RetentionPolicy.RUNTIME)
public @interface PageName {
    /**
     * The name of the annotated page or component class.
     *
     * @return The name of the annotated page or component class. Defaults to an empty string if not provided.
     */

    String value() default "";
}
