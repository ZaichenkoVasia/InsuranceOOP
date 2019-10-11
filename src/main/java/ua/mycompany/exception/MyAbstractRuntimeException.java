package ua.mycompany.exception;

import org.apache.log4j.Logger;

public abstract class MyAbstractRuntimeException extends RuntimeException {
    private static final Logger logger = Logger.getLogger(MyAbstractRuntimeException.class);
    public MyAbstractRuntimeException(String message) {
        System.out.println(message);
        logger.error(message);
    }
}
