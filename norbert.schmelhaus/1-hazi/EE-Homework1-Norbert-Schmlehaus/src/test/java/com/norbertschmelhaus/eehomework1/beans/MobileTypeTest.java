package com.norbertschmelhaus.eehomework1.beans;

import static com.norbertschmelhaus.eehomework1.beans.Color.*;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author norbeee
 */
public class MobileTypeTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    @BeforeClass
    public static void setUpClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Test
    public void shouldRaiseNoConstraintViolation() {
        MobileType mobile = new MobileType(ManufacturerEnum.HTC, "type1", 1, Coin.HUF, GREEN);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseAppleManufacturerWithValidColor() {
        MobileType mobile = new MobileType(ManufacturerEnum.APPLE, "type2", 1, Coin.HUF, BLACK);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseAppleManufacturerWithInvalidColor() {
        MobileType mobile = new MobileType(ManufacturerEnum.APPLE, "type3", 1, Coin.HUF, BLUE);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid color of mobile, apple have only black or white and samsung haven't green color",
                violations.iterator().next().getMessage());
        Assert.assertEquals(mobile, violations.iterator().next().getRootBean());
        Assert.assertEquals("{Manufacturer.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseSamsungManufacturerWithValidColor() {
        MobileType mobile = new MobileType(ManufacturerEnum.SAMSUNG, "type4", 1, Coin.HUF, RED);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseSamsungManufacturerWithInvalidColor() {
        MobileType mobile = new MobileType(ManufacturerEnum.SAMSUNG, "type5", 1, Coin.HUF, GREEN);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid color of mobile, apple have only black or white and samsung haven't green color",
                violations.iterator().next().getMessage());
        Assert.assertEquals(mobile, violations.iterator().next().getRootBean());
        Assert.assertEquals("{Manufacturer.message}", violations.iterator().next().getMessageTemplate());
    }
}
