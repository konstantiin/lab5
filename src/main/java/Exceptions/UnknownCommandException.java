package Exceptions;

public class UnknownCommandException extends Exception {

    public UnknownCommandException(String command_not_found) {
        super(command_not_found);
    }
}
