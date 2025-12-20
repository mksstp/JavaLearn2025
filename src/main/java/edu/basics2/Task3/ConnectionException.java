package edu.basics2.Task3;

public class ConnectionException extends RuntimeException {

    @SuppressWarnings("AvoidNoArgumentSuperConstructorCall")
    public ConnectionException() {
        super();
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
