package com.norbertschmelhaus.eehomework2.interceptor;

import com.norbertschmelhaus.eehomework2.annotation.Validate;
import com.norbertschmelhaus.eehomework2.exceptions.ViolationBeanException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Interceptor
@BeanValidation
public class ValidatorInterception {
    
    
    private static ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private static Validator val = vf.getValidator();
    
    @Inject
    private Logger logger;
    
    @AroundInvoke
    public Object validateBeans(InvocationContext ic) {
        for (Object o : ic.getParameters()) {
            if(o.getClass().isAnnotationPresent(Validate.class)) {
                logger.log(Level.INFO, "Class have Validate annotation.");
                beanValidator(o);
            }
        }
        try {
            return ic.proceed();
        } catch (Exception ex) {
            throw new ViolationBeanException(ex);
        }
    }
    
    public void beanValidator(Object o) {
        Set<ConstraintViolation<Object>> violations = val.validate(o);
        if(violations.isEmpty()) {
            logger.log(Level.INFO, "No violations yet.");
        } else {
            for(ConstraintViolation<Object> e : violations) {
                throw new ViolationBeanException("Violations: " + e.getMessage() + " Parameters: " + e.getInvalidValue().toString() + " Property: " + e.getPropertyPath().toString());
            }
        }
    }
}
