package hotel.alura.domains.model;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValorReservaDTO {
    
    private LocalDate  dataInicio;
    private LocalDate  dataFim;

}
