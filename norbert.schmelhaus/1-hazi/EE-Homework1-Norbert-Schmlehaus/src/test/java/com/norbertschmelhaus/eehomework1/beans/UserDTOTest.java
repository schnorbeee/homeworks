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
    private final Calendar cal = Calendar.getInstance();;
    private final Date now = new Date();
    private Date dateOfBirthValid;
    private Date dateOfBirthInvalid;

    @BeforeClass
    public static void setUpClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Before
    public void setUp() {
        cal.set(1995, 10, 10);
        dateOfBirthValid = cal.getTime();
        cal.set(2016, 10, 10);
        dateOfBirthInvalid = cal.getTime();
    }

    @Test
    public void shouldRaiseNoConstraintViolationCauseValidPassword() {
        UserDTO user = new UserDTO("Janos45", "Ezajelszo>", "email@email.hu", now);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidPassword() {
        UserDTO user = new UserDTO("Janos45", "dummyPassword", "email@email.hu", now);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("dummyPassword", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Password.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseNoConstraintViolationCauseValidAddress() {
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
        Assert.assertEquals("dummyAddress", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Address.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseNoConstraintViolationCauseValidPhone() {
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
        Assert.assertEquals("061234567890", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Phone.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseNoConstraintViolationCauseValidEmail() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "email@email.hu", now);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidEmail() {
        UserDTO user = new UserDTO("Janos45", "Valid541", "@email.hu", now);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("@email.hu", violations.iterator().next().getInvalidValue());
        Assert.assertEquals("{Email.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseNoConstraintViolationCauseValidFirstFillLastFill() {
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
        Assert.assertEquals(user, violations.iterator().next().getRootBean());
        Assert.assertEquals("{FirstFillLastFill.message}", violations.iterator().next().getMessageTemplate());
    }

    @Test
    public void shouldRaiseNoConstraintViolationCauseValidDateOfBirth() {
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
        Assert.assertEquals(user, violations.iterator().next().getRootBean());
        Assert.assertEquals("{DateOfBirth.message}", violations.iterator().next().getMessageTemplate());
    }
}
