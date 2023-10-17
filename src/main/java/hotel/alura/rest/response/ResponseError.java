package hotel.alura.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseError implements Serializable {
    @Serial
    private static final long serialVersionUID= 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;

    private String mensagem;
    private String error;
    private HttpStatus status;

    public ResponseError(String mensagem, String error, HttpStatus status) {
        this.mensagem = mensagem;
        this.error = error;
        this.status = status;
    }

    public ResponseError(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
