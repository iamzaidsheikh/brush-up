package io.github.brushup.cardsservice.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FrontValidator.class)
@Documented
public @interface Front {
    String message() default "Invalid front text. Must be a non empty string with upto 30 characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
