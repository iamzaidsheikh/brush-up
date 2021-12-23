package io.github.brushup.cardsservice.exception;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = CardNotFoundException.class)
    public ResponseEntity<APIError> handle(CardNotFoundException e) {
        APIError error = new APIError(HttpStatus.NOT_FOUND, "Card not found", e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = InvalidIdException.class)
    public ResponseEntity<APIError> handle(InvalidIdException e) {
        APIError error = new APIError(HttpStatus.BAD_REQUEST, "Invalid Id", e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIError> handle(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String errorMessage = "";
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append(" " + violation.getMessage()));
            errorMessage = builder.toString();
        } else {
            errorMessage = "ConstraintViolationException occured.";
        }

        APIError error = new APIError(HttpStatus.BAD_REQUEST, errorMessage, e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = CannotUpdateCardException.class)
    public ResponseEntity<APIError> handle(CannotUpdateCardException e) {
        APIError error = new APIError(HttpStatus.BAD_REQUEST, "Cannot update card", e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handle(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getAllErrors();
        String errorMessage = "";
        if(!errors.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            errors.forEach(error -> builder.append(" " + error.getDefaultMessage() + "."));
            errorMessage = builder.toString();
        }else {
            errorMessage = "MethodArgumentNotValidException occured.";
        }

        APIError error = new APIError(HttpStatus.BAD_REQUEST, errorMessage, e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = MissingUserIdHeaderException.class)
    public ResponseEntity<APIError> handle(MissingUserIdHeaderException e) {
        APIError error = new APIError(HttpStatus.BAD_REQUEST, "User id header is missing", e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = UnauthorizedUserException.class)
    public ResponseEntity<APIError> handle(UnauthorizedUserException e) {
        APIError error = new APIError(HttpStatus.UNAUTHORIZED, "Unauthorized user", e);
        return new ResponseEntity<>(error, error.getStatus());
    }
}
