package com.qt.annotation.values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark a class or method to be skipped during execution.
 * Can be applied to both classes and methods.
 */


@Target({ElementType.TYPE, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
public @interface Skip {
    /**
     * Optional name to specify the reason for skipping.
     *
     * @return The reason for skipping. Defaults to an empty string if not provided.
     */

    String name() default "";
}
