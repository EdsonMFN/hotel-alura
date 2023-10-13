package hotel.alura.domains.model;

import hotel.alura.domains.entities.Reserva;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospedeDto {

    private Long id ;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String nacionalidade;
    private String telefone;
    private List<Reserva> reservas;
}
