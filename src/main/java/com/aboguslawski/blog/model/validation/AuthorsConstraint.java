package com.aboguslawski.blog.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuthorsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorsConstraint {

    String message() default "author not found in the database";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
