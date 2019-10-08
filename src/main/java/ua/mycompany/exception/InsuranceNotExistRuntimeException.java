package ua.mycompany.exception;

public class InsuranceNotExistRuntimeException extends RuntimeException{
    public InsuranceNotExistRuntimeException(String message) {
        super(message);
    }
}
