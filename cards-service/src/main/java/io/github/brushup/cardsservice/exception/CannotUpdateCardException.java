package io.github.brushup.cardsservice.exception;

public class CannotUpdateCardException extends RuntimeException{
    public CannotUpdateCardException(String cardId, String message) {
        super("Cannot update card " + cardId + ". " + message);
    }
}
