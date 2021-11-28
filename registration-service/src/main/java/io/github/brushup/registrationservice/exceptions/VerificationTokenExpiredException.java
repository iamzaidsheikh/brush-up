package io.github.brushup.registrationservice.exceptions;

public class VerificationTokenExpiredException extends RuntimeException{
    
    public VerificationTokenExpiredException(String token) {
        super("Verifcation token: " + token + " has expired");
    }
}

