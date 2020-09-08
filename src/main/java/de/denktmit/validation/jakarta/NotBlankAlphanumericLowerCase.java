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
@Alphanumeric
@LowerCase
@Target({ METHOD, FIELD, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface NotBlankAlphanumericLowerCase {

    String message() default "{io.gec.constraints.notblank_alphanumeric_lowercase}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
