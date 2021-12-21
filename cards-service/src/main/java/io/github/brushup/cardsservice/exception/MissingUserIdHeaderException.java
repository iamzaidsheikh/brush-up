package io.github.brushup.cardsservice.exception;

public class MissingUserIdHeaderException extends RuntimeException {
    public MissingUserIdHeaderException() {
        super("User id header is missing. Must provide user id as header");
    }
}
