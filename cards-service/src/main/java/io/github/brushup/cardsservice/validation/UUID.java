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
@Constraint(validatedBy = UUIDValidator.class)
@Documented
public @interface UUID {
    String message() default "Id provided is not a version 4 UUID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
