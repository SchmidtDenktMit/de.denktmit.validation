package de.denktmit.validation.jakarta.validators;

import de.denktmit.validation.jakarta.Alphanumeric;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class AlphanumericValidator implements ConstraintValidator<Alphanumeric, String> {

    private static final byte ASCII_VALUE_0 = 48;
    private static final byte ASCII_VALUE_9 = 57;
    private static final byte ASCII_VALUE_A = 65;
    private static final byte ASCII_VALUE_Z = 90;
    private static final byte ASCII_VALUE_a = 97;
    private static final byte ASCII_VALUE_z = 122;
    private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("[a-zA-Z0-9]*");
    private static final Set<Character> ALLOWED_CHARACTERS = Arrays.stream(
        new Character[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        }
    ).collect(Collectors.toSet());

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

    public boolean isValidByLookup(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (char aChar : value.toCharArray()) {
            if (!ALLOWED_CHARACTERS.contains(aChar)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidByRegex(String value, ConstraintValidatorContext context) {
        return value == null || ALPHANUMERIC_PATTERN.matcher(value).matches();
    }

}
