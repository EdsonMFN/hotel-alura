package hotel.alura.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import hotel.alura.exception.handling.EntidadeNaoEncontrada;
import hotel.alura.rest.response.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j(topic = "ERROR_HANDLER")
@RestControllerAdvice
public class ErrorHandling extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){

        ResponseError responseError = new ResponseError(ex.getMessage(),"argumento invalido", HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(ex,responseError,headers, status, request);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Request invalida",ex.getCause());

        Object message = null;

        if (ex.getCause() instanceof InvalidFormatException){
            InvalidFormatException exception = (InvalidFormatException) ex.getCause();
            message = exception.getValue();
        }

        return buildErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST,"Request invalida");
    }
    @ExceptionHandler(EntidadeNaoEncontrada.class)
    public ResponseEntity<Object> EntidadeNaoEncontrada(EntidadeNaoEncontrada ex){
        log.error("Entidade não encontrada verifique se está correto");
        logError(ex);

        return buildErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND,"entidade não encontrada");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerErrorGenericException(Exception ex){
        logError(ex);
        log.error("Error na Api!");

        return buildErrorResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private  static void logError(Exception ex){
        log.error(ex.getClass().getName(), ex);
        log.error(ex.getClass().getName(), ex.getMessage());
        log.error(ex.getClass().getName(), ex.getLocalizedMessage());
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus httpStatus, String error) {
        ResponseError responseError = new ResponseError(message,error,httpStatus);
        return ResponseEntity.status(httpStatus).body(responseError);
    }
}
