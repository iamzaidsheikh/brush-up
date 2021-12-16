package io.github.brushup.cardsservice.exception;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException(String cardId) {
        super("No card with id: " + cardId + " found");
    }
}

