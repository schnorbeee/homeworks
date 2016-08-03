package com.norbertschmelhaus.eehomework2.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.norbertschmelhaus.eehomework2.dto.UserDTO;

/**
 *
 * @author norbeee
 */
public class DateOfBirthValidator implements ConstraintValidator<DateOfBirth, UserDTO> {

    @Override
    public void initialize(DateOfBirth a) {
        //This is only initializator annotation
    }

    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext cvc) {
        if (value.getDateOfBirth() != null) {
            return value.getDateOfBirth().before(value.getRegistrationDate());
        }
        return true;
    }
}
