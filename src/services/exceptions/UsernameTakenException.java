package services.exceptions;

public class UsernameTakenException extends RuntimeException{
    public UsernameTakenException(String message) {
        super(message);
    }
}