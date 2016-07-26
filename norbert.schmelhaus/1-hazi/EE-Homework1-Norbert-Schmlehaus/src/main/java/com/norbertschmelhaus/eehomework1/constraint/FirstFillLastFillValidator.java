package com.norbertschmelhaus.eehomework1.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.norbertschmelhaus.eehomework1.dto.UserDTO;

/**
 *
 * @author norbeee
 */
public class FirstFillLastFillValidator implements ConstraintValidator<FirstFillLastFill, UserDTO> {

    @Override
    public void initialize(FirstFillLastFill constraintAnnotation) {
        //This is only initializator annotation
    }

    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
        if (value.getFirstName() != null) {
            return value.getLastName() != null;
        }
        return true;
    }
}
