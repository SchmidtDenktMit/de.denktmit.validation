package de.denktmit.validation.jakarta;

import de.denktmit.validation.jakarta.validators.AlphanumericValidator;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AlphanumericValidatorTest {

    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String DISALLOWED_CHARACTER_SAMPLES = "    ^°„“!\"§$%&/()=?´``“¶¢[]|{}≠¿'  üöä+#ÜÖÄ*'±‘æœ•@-.,_:;–…∞—÷˛»„≥‡";
    private static final AlphanumericValidator ALPHANUMERIC_VALIDATOR = new AlphanumericValidator();

    @Test
    void testValidationSucceedsWithNullValue() {
        assertThat(ALPHANUMERIC_VALIDATOR.isValid(null, null)).isTrue();
    }

    @Test
    void testValidationSucceedsWithBlankValue() {
        assertThat(ALPHANUMERIC_VALIDATOR.isValid("", null)).isTrue();
    }

    @Test
    void testValidationSucceedsWithSingleAllowedCharacters() {
        for (Character aChar : ALLOWED_CHARACTERS.toCharArray()) {
            assertThat(ALPHANUMERIC_VALIDATOR.isValid(aChar.toString(), null)).isTrue();
        }
    }

    @Test
    void testValidationSucceedsWithAllowedCharacters() {
        assertThat(ALPHANUMERIC_VALIDATOR.isValid(ALLOWED_CHARACTERS, null)).isTrue();
    }

    @Test
    void testValidationFailsForAnySampleDisallowedCharacter() {
        for (Character aChar : DISALLOWED_CHARACTER_SAMPLES.toCharArray()) {
            assertThat(ALPHANUMERIC_VALIDATOR.isValid(aChar.toString(), null)).isFalse();
        }
    }

    @Test
    void testAnnotatedValidationSucceedsWithBlankValue() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Sample>> violations = validator.validate(new Sample(ALLOWED_CHARACTERS));
        assertThat(violations).isEmpty();
    }

    @Test
    void testAnnotatedValidationSucceedsWithAllAllowedCharacters() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Sample>> violations = validator.validate(new Sample(DISALLOWED_CHARACTER_SAMPLES));
        assertThat(violations)
            .hasOnlyElementsOfType(ConstraintViolation.class)
            .hasSize(1)
            .first().extracting("propertyPath").hasToString("someString");
    }

    @Test
    void tenthousandRoundsRegex() {
        String longString = "";
        for (int i = 0; i < 1000; i++) {
            longString += ALLOWED_CHARACTERS;
        }
        for (int i = 0; i < 10000; i++) {
            ALPHANUMERIC_VALIDATOR.isValidByRegex(longString, null);
        }
    }

    @Test
    void tenthousandRoundsLookup() {
        String longString = "";
        for (int i = 0; i < 1000; i++) {
            longString += ALLOWED_CHARACTERS;
        }
        for (int i = 0; i < 10000; i++) {
            ALPHANUMERIC_VALIDATOR.isValidByLookup(longString, null);
        }
    }

    @Test
    void tenthousandRoundsRange() {
        String longString = "";
        for (int i = 0; i < 1000; i++) {
            longString += ALLOWED_CHARACTERS;
        }
        for (int i = 0; i < 10000; i++) {
            ALPHANUMERIC_VALIDATOR.isValid(longString, null);
        }
    }

    private static class Sample{
        @Alphanumeric
        private final String someString;
        private Sample(String someString) {
            this.someString = someString;
        }
    }

}
