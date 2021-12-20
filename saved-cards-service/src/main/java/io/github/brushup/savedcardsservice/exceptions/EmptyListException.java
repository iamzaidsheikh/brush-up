package io.github.brushup.savedcardsservice.exceptions;

public class EmptyListException extends RuntimeException{
    public EmptyListException() {
        super("List of card ids is empty");
    }
}
