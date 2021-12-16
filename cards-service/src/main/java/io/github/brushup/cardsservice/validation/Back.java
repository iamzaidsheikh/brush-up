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
@Constraint(validatedBy = BackValidator.class)
@Documented
public @interface Back {
    String message() default "Invalid back text. Only upto 140 characters are allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
