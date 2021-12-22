package io.github.brushup.savedcardsservice.exceptions;

public class MissingUserIdHeaderException extends RuntimeException {
    public MissingUserIdHeaderException() {
        super("User id header is missing. Must provide user id as header");
    }
}
