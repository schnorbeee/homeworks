package com.norbertschmelhaus.eehomework1.constraint;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

/**
 *
 * @author norbeee
 */
@Pattern(regexp = "^\\d{4}.*")
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target(FIELD)
@Retention(RUNTIME)
public @interface Address {

    String message() default "{Address.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(FIELD)
    @Retention(RUNTIME)
    @interface List {

        Address[] value();
    }
}
