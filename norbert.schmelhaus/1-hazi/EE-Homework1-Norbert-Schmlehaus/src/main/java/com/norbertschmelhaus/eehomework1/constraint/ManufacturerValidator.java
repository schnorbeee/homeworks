package com.norbertschmelhaus.eehomework1.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.norbertschmelhaus.eehomework1.beans.MobileType;
import com.norbertschmelhaus.eehomework1.beans.ManufacturerEnum;
import com.norbertschmelhaus.eehomework1.beans.Color;

/**
 *
 * @author norbeee
 */
public class ManufacturerValidator implements ConstraintValidator<Manufacturer, MobileType> {

    @Override
    public void initialize(Manufacturer constraintAnnotation) {
        //This is only initializator annotation
    }

    @Override
    public boolean isValid(MobileType value, ConstraintValidatorContext cvc) {
        if (value.getManufacturer().equals(ManufacturerEnum.APPLE)) {
            return value.getColor().equals(Color.BLACK) || value.getColor().equals(Color.WHITE);
        }
        if (value.getManufacturer().equals(ManufacturerEnum.SAMSUNG)) {
            return !value.getColor().equals(Color.GREEN);
        }
        return true;
    }
}
