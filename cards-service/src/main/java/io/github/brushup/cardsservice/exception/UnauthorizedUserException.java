package io.github.brushup.cardsservice.exception;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException(String userId, String cardId) {
        super("User: " + userId + " is not the author of the card: " + cardId);
    }
}
