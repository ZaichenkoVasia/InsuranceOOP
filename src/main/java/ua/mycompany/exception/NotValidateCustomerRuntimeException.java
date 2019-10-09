package ua.mycompany.exception;

public class NotValidateCustomerRuntimeException extends RuntimeException {
    public NotValidateCustomerRuntimeException(String message) {
        super(message);
    }
}
