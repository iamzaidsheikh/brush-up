package io.github.brushup.cardsservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FrontValidator implements ConstraintValidator<Front, String> {

    private boolean validateFront(String frontText) {
        if(frontText == null)
            return false;
        if(frontText.isBlank())
            return false;
        if(frontText.length() > 30)
            return false;
            
        return true;    
    }

    @Override
    public boolean isValid(String frontText, ConstraintValidatorContext context) {
        return (validateFront(frontText));
    }
    
}
