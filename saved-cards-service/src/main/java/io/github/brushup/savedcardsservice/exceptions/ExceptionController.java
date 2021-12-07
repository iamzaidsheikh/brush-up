package io.github.brushup.savedcardsservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(value = NoSavedCardsFoundException.class)
    public ResponseEntity<APIError> exception(NoSavedCardsFoundException e) {
        APIError error = new APIError(HttpStatus.NOT_FOUND, "No saved cards found", e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = InvalidIdException.class)
    public ResponseEntity<APIError> exception(InvalidIdException e) {
        APIError error = new APIError(HttpStatus.BAD_REQUEST, "Invalid Id", e);
        return new ResponseEntity<>(error, error.getStatus());
    }
}