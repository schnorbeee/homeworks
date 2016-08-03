package com.norbertschmelhaus.eehomework2.annotation;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Validate {
}
