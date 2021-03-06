package de.denktmit.validation.jakarta;

import de.denktmit.validation.jakarta.validators.UpperCaseValidator;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.extractor.Extractors.toStringMethod;

class UpperCaseValidatorTest {

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DISALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final UpperCaseValidator VALIDATOR = new UpperCaseValidator();

    @Test
    void testValidationSucceedsWithNullValue() {
        assertThat(VALIDATOR.isValid(null, null)).isTrue();
    }

    @Test
    void testValidationSucceedsWithBlankValue() {
        assertThat(VALIDATOR.isValid("", null)).isTrue();
    }

    @Test
    void testValidationSucceedsWithSingleAllowedCharacters() {
        for (Character aChar : ALLOWED_CHARACTERS.toCharArray()) {
            assertThat(VALIDATOR.isValid(aChar.toString(), null)).isTrue();
        }
    }

    @Test
    void testValidationSucceedsWithAllowedCharacters() {
        assertThat(VALIDATOR.isValid(ALLOWED_CHARACTERS, null)).isTrue();
    }

    @Test
    void testValidationFailsForAnySampleDisallowedCharacter() {
        for (Character aChar : DISALLOWED_CHARACTERS.toCharArray()) {
            assertThat(VALIDATOR.isValid(aChar.toString(), null)).isFalse();
        }
    }

    @Test
    void testAnnotatedValidationSucceedsWithBlankValue() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Sample>> violations = validator.validate(new Sample(ALLOWED_CHARACTERS));
        assertThat(violations).isEmpty();
    }

    @Test
    void testAnnotatedValidationFailsWithAllAllowedCharacters() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Sample>> violations = validator.validate(new Sample(DISALLOWED_CHARACTERS));
        assertThat(violations)
            .hasOnlyElementsOfType(ConstraintViolation.class)
            .hasSize(3)
            .extracting("propertyPath")
            .extracting(toStringMethod())
            .contains("a", "b", "c");
    }

    private static class Sample{
        @UpperCase
        private final String a;
        @UpperCase
        private final CharSequence b;
        @UpperCase
        private final StringBuffer c;
        private Sample(String value) {
            this.a = value;
            this.b = value;
            this.c = new StringBuffer(value);
        }
    }

}
