package de.denktmit.validation.jakarta;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotBlank
@LowerCase
@Alphanumeric
@Target({ METHOD, FIELD, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface NotBlankLowerCaseAlphanumeric {

    String message() default "{io.gec.constraints.notblank_lowercase_alphanumeric}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
