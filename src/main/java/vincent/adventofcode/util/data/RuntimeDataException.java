package vincent.adventofcode.util.data;

public class RuntimeDataException extends RuntimeException {
    public RuntimeDataException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RuntimeDataException(Exception e) {
        super(e);
    }
}
