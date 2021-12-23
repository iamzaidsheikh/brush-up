package io.github.brushup.savedcardsservice.exceptions;

public class CardIsNotSavedException extends RuntimeException{
    public CardIsNotSavedException(String cardId) {
        super("Cannot removed card: " + cardId + "as it is not saved.");
    }
}
