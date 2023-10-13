package hotel.alura.rest.response;

import hotel.alura.domains.model.HospedeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseHospede {

    private HospedeDto hospedeDto;

    public ResponseHospede(HospedeDto hospedeDto) {
        this.hospedeDto = hospedeDto;
    }
}
