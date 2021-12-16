package io.github.brushup.cardsservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BackValidator implements ConstraintValidator<Back, String> {

    private boolean validateBack(String backText) {
        if(backText == null)
            return false;
        if(backText.isBlank())
            return false;
        if(backText.length() > 140)
            return false;
        
        return true;    
    }

    @Override
    public boolean isValid(String backText, ConstraintValidatorContext context) {
        return (validateBack(backText));
    }
    
}
