package hotel.alura.exception;

public class EntidadeNaoEncontrada extends RuntimeException{

    public EntidadeNaoEncontrada(String s) {
        super(s);
    }

    public EntidadeNaoEncontrada(String s, Throwable throwable) {
        super(s, throwable);
    }
}
