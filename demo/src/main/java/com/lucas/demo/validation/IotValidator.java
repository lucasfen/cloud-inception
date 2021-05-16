package com.lucas.demo.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;

/**
 * @author Lucasfen
 * @Date 2021/05/09
 */
@Slf4j
public class IotValidator {

    public IotValidator(){}

    private static Validator validator =
            Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    public static <T> void validate(T target) {
        Set<ConstraintViolation<T>> violations = validator.validate(target);
        checkResult(violations);
    }

    private static <T> void checkResult(Set<ConstraintViolation<T>> constraintViolations) {
        if (!constraintViolations.isEmpty()) {
            String msg = ((ConstraintViolation)constraintViolations.iterator().next()).getMessage();
           System.out.println(msg);
        }
    }
}
