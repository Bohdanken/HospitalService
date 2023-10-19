package ukma.edu.ua.HospitalApp.exceptionhandler;

public class IncorrectIDException extends RuntimeException {

    private static final String message = "Incorrect id";

    public IncorrectIDException(Throwable err) {
        super(message, err);
    }

    public IncorrectIDException(String message, Throwable err) {
        super(message, err);
    }
}