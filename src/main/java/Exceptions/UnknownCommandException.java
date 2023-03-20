package Exceptions;

public class UnknownCommandException extends RuntimeException {

    public UnknownCommandException(String command_not_found) {
        super(command_not_found);
    }
}
