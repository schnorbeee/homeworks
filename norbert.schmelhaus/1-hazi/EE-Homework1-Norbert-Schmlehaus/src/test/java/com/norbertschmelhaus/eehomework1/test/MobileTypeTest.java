package com.norbertschmelhaus.eehomework1.test;

import com.norbertschmelhaus.eehomework1.enums.ManufacturerEnum;
import com.norbertschmelhaus.eehomework1.enums.Coin;
import com.norbertschmelhaus.eehomework1.dto.MobileType;
import static com.norbertschmelhaus.eehomework1.enums.Color.*;
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
    public void shouldRaiseNoConstraintViolationCauseAppleManufacturerWithValidColor() {
        MobileType mobile = new MobileType(ManufacturerEnum.APPLE, "type2", 1, Coin.HUF, BLACK);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseAppleManufacturerWithInvalidColor() {
        MobileType mobile = new MobileType(ManufacturerEnum.APPLE, "type3", 1, Coin.HUF, BLUE);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(mobile, violations.iterator().next().getRootBean());
        Assert.assertEquals("{Manufacturer.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseNoConstraintViolationCauseSamsungManufacturerWithValidColor() {
        MobileType mobile = new MobileType(ManufacturerEnum.SAMSUNG, "type4", 1, Coin.HUF, RED);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseSamsungManufacturerWithInvalidColor() {
        MobileType mobile = new MobileType(ManufacturerEnum.SAMSUNG, "type5", 1, Coin.HUF, GREEN);
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobile);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(mobile, violations.iterator().next().getRootBean());
        Assert.assertEquals("{Manufacturer.message}", violations.iterator().next().getMessageTemplate());
    }
}
