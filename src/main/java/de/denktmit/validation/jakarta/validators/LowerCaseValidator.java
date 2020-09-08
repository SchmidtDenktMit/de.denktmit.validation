package de.denktmit.validation.jakarta.validators;

import de.denktmit.validation.jakarta.LowerCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public final class LowerCaseValidator implements ConstraintValidator<LowerCase, String> {

    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        return value == null || value.equals(value.toLowerCase());
    }

}
