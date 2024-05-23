package com.qt.annotation.values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation used to provide a description for a field.
 * Can be applied to fields.
 */


@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
public @interface ElementDescription {
    /**
     * The description of the annotated field.
     *
     * @return The description of the annotated field. Defaults to an empty string if not provided.
     */

    String value() default "";
}
