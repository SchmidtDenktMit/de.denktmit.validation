package de.denktmit.validation.jakarta;

import de.denktmit.validation.jakarta.validators.AlphanumericValidator;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.extractor.Extractors.toStringMethod;

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
            .hasSizeGreaterThanOrEqualTo(3)
            .extracting("propertyPath")
            .extracting(toStringMethod())
            .contains("a", "b", "c");
    }

    private static class Sample{
        @Alphanumeric
        private final String a;
        @Alphanumeric
        private final CharSequence b;
        @Alphanumeric
        private final StringBuffer c;
        private Sample(String value) {
            this.a = value;
            this.b = value;
            this.c = new StringBuffer(value);
        }
    }

}
