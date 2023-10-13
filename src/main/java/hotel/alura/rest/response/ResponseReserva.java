package hotel.alura.rest.response;

import hotel.alura.domains.model.ReservaDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseReserva {

    private ReservaDto reservaDto;

    public ResponseReserva(ReservaDto reservaDto) {
        this.reservaDto = reservaDto;
    }
}
