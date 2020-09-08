package de.denktmit.validation.jakarta;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NotBlankLowerCaseAlphanumericValidatorTest {

    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String DISALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ    ^°„“!\"§$%&/()=?´``“¶¢[]|{}≠¿'  üöä+#ÜÖÄ*'±‘æœ•@-.,_:;–…∞—÷˛»„≥‡";

    @Test
    void testValidationFailsWithNullValue() {
        assertValidatorFails(null);
    }

    @Test
    void testValidationFailsWithBlankValue() {
        assertValidatorFails("");
    }

    @Test
    void testValidationFailsForAnySampleDisallowedCharacter() {
        for (Character aChar : DISALLOWED_CHARACTERS.toCharArray()) {
            assertValidatorFails(aChar.toString());
        }
    }

    private void assertValidatorFails(String value) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Sample>> violations = validator.validate(new Sample(value));
        assertThat(violations)
            .hasOnlyElementsOfType(ConstraintViolation.class)
            .hasSizeGreaterThanOrEqualTo(1)
            .first().extracting("propertyPath").hasToString("someString");
    }

    @Test
    void testValidationSucceedsWithSingleAllowedCharacters() {
        for (Character aChar : ALLOWED_CHARACTERS.toCharArray()) {
            assertValidatorSucceeds(aChar.toString());
        }
    }

    @Test
    void testValidationSucceedsWithAllowedCharacters() {
        assertValidatorSucceeds(ALLOWED_CHARACTERS);
    }

    private void assertValidatorSucceeds(String value) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Sample>> violations = validator.validate(new Sample(value));
        assertThat(violations).isEmpty();
    }

    private static class Sample{
        @NotBlankLowerCaseAlphanumeric
        private final String someString;
        private Sample(String someString) {
            this.someString = someString;
        }
    }

}
