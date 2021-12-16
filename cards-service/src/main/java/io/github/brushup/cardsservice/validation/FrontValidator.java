package io.github.brushup.cardsservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FrontValidator implements ConstraintValidator<Front, String> {

    private boolean validateFront(String frontText) {
        if(frontText.length() <= 30)
            return true;
        else
            return false;    
    }

    @Override
    public boolean isValid(String frontText, ConstraintValidatorContext context) {
        return (validateFront(frontText));
    }
    
}
