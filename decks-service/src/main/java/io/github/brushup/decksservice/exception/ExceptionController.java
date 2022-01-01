package io.github.brushup.decksservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(value = DeckNotFoundException.class)
    public ResponseEntity<APIError> handle(DeckNotFoundException e) {
        APIError error = new APIError(HttpStatus.NOT_FOUND, "Could not find deck", e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = InvalidIdException.class)
    public ResponseEntity<APIError> handle(InvalidIdException e) {
        APIError error = new APIError(HttpStatus.BAD_REQUEST, "Invalid Id", e);
        return new ResponseEntity<>(error, error.getStatus());
    }
}
