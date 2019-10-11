package ua.mycompany.exception;

public class CustomerNotExistRuntimeException extends MyAbstractRuntimeException {
    public CustomerNotExistRuntimeException(String message) {
        super(message);
    }
}
