package io.github.brushup.savedcardsservice.exceptions;

public class InvalidIdException extends RuntimeException{
    public InvalidIdException(String id) {
        super("Invalid id. id: " + id + " is not a valid version 4 UUID");
    }
}
