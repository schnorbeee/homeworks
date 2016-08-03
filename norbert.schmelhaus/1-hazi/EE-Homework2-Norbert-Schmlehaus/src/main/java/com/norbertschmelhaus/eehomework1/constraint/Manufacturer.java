package com.norbertschmelhaus.eehomework2.constraint;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author norbeee
 */
@Constraint(validatedBy = ManufacturerValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface Manufacturer {

    String message() default "{Manufacturer.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
