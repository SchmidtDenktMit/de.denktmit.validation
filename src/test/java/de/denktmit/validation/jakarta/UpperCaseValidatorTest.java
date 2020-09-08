package de.denktmit.validation.jakarta;

import de.denktmit.validation.jakarta.validators.UpperCaseValidator;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
    void testAnnotatedValidationSucceedsWithAllAllowedCharacters() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Sample>> violations = validator.validate(new Sample(DISALLOWED_CHARACTERS));
        assertThat(violations)
            .hasOnlyElementsOfType(ConstraintViolation.class)
            .hasSize(1)
            .first().extracting("propertyPath").hasToString("someString");
    }

    private static class Sample{
        @UpperCase
        private final String someString;
        private Sample(String someString) {
            this.someString = someString;
        }
    }

}
