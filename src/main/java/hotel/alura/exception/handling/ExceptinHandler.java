package hotel.alura.exception.handling;

public class ExceptinHandler extends RuntimeException {
    public ExceptinHandler(String s) {
        super(s);
    }

    public ExceptinHandler(String s, Throwable throwable) {
        super(s, throwable);
    }
}
