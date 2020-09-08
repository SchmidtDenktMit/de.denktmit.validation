package de.denktmit.validation.jakarta.validators;

import de.denktmit.validation.jakarta.Alphanumeric;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public final class AlphanumericValidator implements ConstraintValidator<Alphanumeric, CharSequence> {

    private static final byte ASCII_VALUE_0 = 48;
    private static final byte ASCII_VALUE_9 = 57;
    private static final byte ASCII_VALUE_A = 65;
    private static final byte ASCII_VALUE_Z = 90;
    private static final byte ASCII_VALUE_a = 97;
    private static final byte ASCII_VALUE_z = 122;

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.chars().allMatch((x) -> isInAlphanumericRange(x));
    }

    private boolean isInAlphanumericRange(int charInteger) {
        return (charInteger >= ASCII_VALUE_0 && charInteger <= ASCII_VALUE_9)
            || (charInteger >= ASCII_VALUE_A && charInteger <= ASCII_VALUE_Z)
            || (charInteger >= ASCII_VALUE_a && charInteger <= ASCII_VALUE_z);
    }

}
