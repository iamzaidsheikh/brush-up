package io.github.brushup.cardsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(value = CardNotFoundException.class)
    public ResponseEntity<APIError> exception(CardNotFoundException e) {
        APIError error = new APIError(HttpStatus.NOT_FOUND, "Card not found", e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = InvalidIdException.class)
    public ResponseEntity<APIError> exception(InvalidIdException e) {
        APIError error = new APIError(HttpStatus.BAD_REQUEST, "Invalid Id", e);
        return new ResponseEntity<>(error, error.getStatus());
    }
}
