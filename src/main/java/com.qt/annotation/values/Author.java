package com.qt.annotation.values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to specify the author of a class or method.
 * Can be applied to both classes and methods.
 */


@Target({ElementType.TYPE, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)

public @interface Author {

    /**
     *
     * @return The name of the author. Defaults to an empty string if not provided.
     */

    String name() default "";
}
