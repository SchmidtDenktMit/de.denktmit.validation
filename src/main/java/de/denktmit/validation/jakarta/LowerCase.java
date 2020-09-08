package de.denktmit.validation.jakarta;

import de.denktmit.validation.jakarta.validators.LowerCaseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = LowerCaseValidator.class)
@Documented
public @interface LowerCase {

    String message() default "{io.gec.constraints.lowercase}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
