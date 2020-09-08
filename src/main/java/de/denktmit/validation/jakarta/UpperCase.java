package de.denktmit.validation.jakarta;

import de.denktmit.validation.jakarta.validators.UpperCaseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated element must only contain non-alphabetic or upper case alphabetic characters of ASCII range A - Z.
 *
 * @author Marius Schmidt
 * @since 1.0.0
 *
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UpperCaseValidator.class)
@Documented
public @interface UpperCase {

    String message() default "{io.gec.constraints.lowercase}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
