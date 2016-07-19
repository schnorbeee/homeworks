package com.norbertschmelhaus.eehomework1.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author norbeee
 */
public class UserDTOTest {

    private static ValidatorFactory vf;
    private static Validator validator;
    private static Calendar cal;
    private static Date now;
    private static Date dateOfBirthValid;
    private static Date dateOfBirthInvalid;

    @BeforeClass
    public static void setUpClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
        cal = Calendar.getInstance();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Before
    public void setUp() {
        now = new Date();
        cal.set(1995, 10, 10);
        dateOfBirthValid = cal.getTime();
        cal.set(2016, 10, 10);
        dateOfBirthInvalid = cal.getTime();
    }

    @Test
    public void shouldRaiseConstraintViolationCauseValidPassword() {
        UserDTO user = new UserDTO("Janos45", "Ezajelszo>", "email@email.hu", now);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidPassword() {
        UserDTO user = new UserDTO("Janos45", "dummyPassword", "email@email.hu", now);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid password, must have lowwercase,uppercase & number or scpec caracter and min size 6",
                violations.iterator().next().getMessage());
        Assert.assertEquals("dummyPassword", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Password.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseValidAddress() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        user.setAddress("8500 Valahol Jó utca 15.");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidAddress() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        user.setAddress("dummyAddress");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid address must be start with 4 numbers",
                violations.iterator().next().getMessage());
        Assert.assertEquals("dummyAddress", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Address.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseValidPhone() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        user.setPhone("+36123456789");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidPhone() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        user.setPhone("061234567890");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid phone number must be start with +36 or 06 and then 9 numbers",
                violations.iterator().next().getMessage());
        Assert.assertEquals("061234567890", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Phone.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseValidEmail() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidEmail() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "@email.hu", now);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid email address",
                violations.iterator().next().getMessage());
        Assert.assertEquals("@email.hu", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Email.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseValidFirstFillLastFill() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        user.setFirstName("Nagy");
        user.setLastName("János");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidFirstFillLastFill() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        user.setFirstName("Nagy");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Missing your lastname pls write it or delete your firstname",
                violations.iterator().next().getMessage());
        Assert.assertEquals(user, violations.iterator().next().getRootBean());
        Assert.assertEquals("{FirstFillLastFill.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseValidDateOfBirth() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        user.setDateOfBirth(dateOfBirthValid);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidDateOfBirth() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        user.setDateOfBirth(dateOfBirthInvalid);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid date of birth, your birth of date must be earlier then registration date",
                violations.iterator().next().getMessage());
        Assert.assertEquals(user, violations.iterator().next().getRootBean());
        Assert.assertEquals("{DateOfBirth.message}", violations.iterator().next().getMessageTemplate());
    }
}
