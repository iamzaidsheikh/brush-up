package io.github.brushup.savedcardsservice.exceptions;

public class NoSavedCardsFoundException extends RuntimeException{
    public NoSavedCardsFoundException(String id) {
        super("No saved cards for user: " + id + " found");
    }
}
