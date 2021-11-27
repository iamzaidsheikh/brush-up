package io.github.brushup.registrationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    public ResponseEntity<RegistrationError> exception(UsernameAlreadyExistsException e) {
        RegistrationError error = new RegistrationError(HttpStatus.CONFLICT, "Username already exists", e);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<RegistrationError> exception(EmailAlreadyExistsException e) {
        RegistrationError error = new RegistrationError(HttpStatus.CONFLICT, "Email already exists", e);
        return new ResponseEntity<>(error, error.getStatus());
    }
}
