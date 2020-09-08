package de.denktmit.validation.jakarta;

import de.denktmit.validation.jakarta.validators.AlphanumericValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = AlphanumericValidator.class)
@Documented
public @interface Alphanumeric {

    String message() default "{io.gec.constraints.alphanumeric}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
