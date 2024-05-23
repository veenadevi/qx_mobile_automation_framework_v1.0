package com.qt.annotation.values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to provide a description for a class or method.
 * Can be applied to both classes and methods.
 */

@Target({ElementType.TYPE, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    /**
     * The description of the annotated class or method.
     *
     * @return The description of the annotated class or method. Defaults to an empty string if not provided.
     */

    String value() default "";
}
