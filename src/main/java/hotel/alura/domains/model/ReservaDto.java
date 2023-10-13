package hotel.alura.domains.model;

import hotel.alura.domains.entities.Hospede;
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
public class ReservaDto {

    private Long id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private Double valor;
    private String formaPagamento;
    private List<Hospede> hospedes;

}
