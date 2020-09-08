package de.denktmit.validation.jakarta.validators;

import de.denktmit.validation.jakarta.UpperCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpperCaseValidator implements ConstraintValidator<UpperCase, String> {

    private static final byte ASCII_VALUE_a = 97;
    private static final byte ASCII_VALUE_z = 122;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (byte aByte : value.getBytes()) {
            if (aByte >= ASCII_VALUE_a && aByte <= ASCII_VALUE_z) {
                return false;
            }
        }
        return true;
    }
}
