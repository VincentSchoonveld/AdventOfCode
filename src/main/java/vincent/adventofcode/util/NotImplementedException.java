package vincent.adventofcode.util;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
        super("This is a utility runtime exception to show something is not (yet) implemented");
    }
}
