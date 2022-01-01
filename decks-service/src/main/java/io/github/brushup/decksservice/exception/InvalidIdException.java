package io.github.brushup.decksservice.exception;

public class InvalidIdException extends RuntimeException{
    public InvalidIdException(String id) {
        super("Invalid id. Id: " + id + " is not a valid version 4 UUID");
    }
}
