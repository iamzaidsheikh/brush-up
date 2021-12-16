package io.github.brushup.cardsservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BackValidator implements ConstraintValidator<Back, String> {

    private boolean validateBack(String backText) {
        if(backText.length() <= 140)
            return true;
        else
            return false;    
    }

    @Override
    public boolean isValid(String backText, ConstraintValidatorContext context) {
        return (validateBack(backText));
    }
    
}
