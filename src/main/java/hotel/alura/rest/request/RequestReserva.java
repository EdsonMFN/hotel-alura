package hotel.alura.rest.request;

import hotel.alura.domains.entities.Hospede;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class RequestReserva {

    private Long id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private Double valor;
    private String formaPagamento;
    private List<Hospede> hospedes;
}
