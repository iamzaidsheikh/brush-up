package io.github.brushup.registrationservice.exceptions;

public class VerificationTokenDoesNotExistException extends RuntimeException {

    public VerificationTokenDoesNotExistException(String token) {
        super("Verification token: " + token + " does not exist");
    }
}
