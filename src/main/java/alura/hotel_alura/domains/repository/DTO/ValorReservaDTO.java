package com.alura.hotel_alura.repository.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValorReservaDTO {
    
    private LocalDate  dataInicio;
    private LocalDate  dataFim;

}
