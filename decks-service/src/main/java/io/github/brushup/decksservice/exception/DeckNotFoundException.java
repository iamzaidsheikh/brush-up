package io.github.brushup.decksservice.exception;

public class DeckNotFoundException extends RuntimeException{
    public DeckNotFoundException(String deckId) {
        super("No deck with id: " + deckId + " found");
    }
}
