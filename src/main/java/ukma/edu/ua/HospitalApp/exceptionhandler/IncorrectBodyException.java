package ukma.edu.ua.HospitalApp.exceptionhandler;

import java.util.List;

public class IncorrectBodyException extends RuntimeException {

    private final List<String> errors;

    public IncorrectBodyException(String message, Throwable err, List<String> errors) {
        super(message, err);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}