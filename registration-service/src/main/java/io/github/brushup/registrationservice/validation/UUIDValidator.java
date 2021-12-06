package io.github.brushup.registrationservice.validation;

import java.util.UUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UUIDValidator implements ConstraintValidator<io.github.brushup.registrationservice.validation.UUID, String> {

    private boolean validateUUID(String id) {
        try{
            UUID.fromString(id);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        return (validateUUID(id));
    }
    
}
