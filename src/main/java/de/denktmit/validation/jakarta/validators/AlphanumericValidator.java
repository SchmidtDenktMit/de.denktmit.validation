package de.denktmit.validation.jakarta.validators;

import de.denktmit.validation.jakarta.Alphanumeric;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public final class AlphanumericValidator implements ConstraintValidator<Alphanumeric, String> {

    private static final byte ASCII_VALUE_0 = 48;
    private static final byte ASCII_VALUE_9 = 57;
    private static final byte ASCII_VALUE_A = 65;
    private static final byte ASCII_VALUE_Z = 90;
    private static final byte ASCII_VALUE_a = 97;
    private static final byte ASCII_VALUE_z = 122;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (byte aByte : value.getBytes()) {
            if (!isInAlphanumericRange(aByte)) {
                return false;
            }
        }
        return true;
    }

    private boolean isInAlphanumericRange(byte aByte) {
        return (aByte >= ASCII_VALUE_0 && aByte <= ASCII_VALUE_9)
            || (aByte >= ASCII_VALUE_A && aByte <= ASCII_VALUE_Z)
            || (aByte >= ASCII_VALUE_a && aByte <= ASCII_VALUE_z);
    }

}
