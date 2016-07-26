package com.norbertschmelhaus.eehomework1.constraint;

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
@Constraint(validatedBy = FirstFillLastFillValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface FirstFillLastFill {

    String message() default "{FirstFillLastFill.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
