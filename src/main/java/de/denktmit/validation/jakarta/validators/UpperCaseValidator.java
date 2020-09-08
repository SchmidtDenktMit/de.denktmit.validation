package de.denktmit.validation.jakarta.validators;

import de.denktmit.validation.jakarta.UpperCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public final class UpperCaseValidator implements ConstraintValidator<UpperCase, CharSequence> {

    private static final byte ASCII_VALUE_a = 97;
    private static final byte ASCII_VALUE_z = 122;

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.chars().noneMatch((x) -> (x >= ASCII_VALUE_a && x <= ASCII_VALUE_z));
    }
}
