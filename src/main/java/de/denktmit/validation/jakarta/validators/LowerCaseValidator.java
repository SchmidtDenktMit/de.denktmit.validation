package de.denktmit.validation.jakarta.validators;

import de.denktmit.validation.jakarta.LowerCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public final class LowerCaseValidator implements ConstraintValidator<LowerCase, CharSequence> {

    private static final byte ASCII_VALUE_A = 65;
    private static final byte ASCII_VALUE_Z = 90;

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.chars().noneMatch((x) -> (x >= ASCII_VALUE_A && x <= ASCII_VALUE_Z));
    }

}
