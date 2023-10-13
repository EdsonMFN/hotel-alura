package hotel.alura.rest.request;

import hotel.alura.domains.entities.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestHospede {

    private Long id ;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String nacionalidade;
    private String telefone;
    private List<Reserva> reservas;
}
