package de.denktmit.validation.jakarta.validators;

import de.denktmit.validation.jakarta.LowerCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public final class LowerCaseValidator implements ConstraintValidator<LowerCase, String> {

    private static final byte ASCII_VALUE_A = 65;
    private static final byte ASCII_VALUE_Z = 90;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (byte aByte : value.getBytes()) {
            if (aByte >= ASCII_VALUE_A && aByte <= ASCII_VALUE_Z) {
                return false;
            }
        }
        return true;
    }

}
