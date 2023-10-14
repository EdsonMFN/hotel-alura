package hotel.alura.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hotel.alura.domains.entities.Reserva;
import hotel.alura.domains.model.ReservaDto;
import hotel.alura.exception.EntidadeNaoEncontrada;
import hotel.alura.exception.ExceptinHandler;
import hotel.alura.rest.request.RequestReserva;
import hotel.alura.rest.response.ResponseReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.alura.domains.repository.ReservaRepository;
import hotel.alura.domains.model.ValorReservaDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    public ResponseReserva insertReserva(RequestReserva requestReserva){

        Reserva reserva = new Reserva();
        reserva.setDataEntrada(requestReserva.getDataEntrada());
        reserva.setDataSaida(requestReserva.getDataSaida());
        reserva.setFormaPagamento(requestReserva.getFormaPagamento());
        reserva.setValor(valorEstadia(ValorReservaDTO.builder().dataInicio(reserva.getDataEntrada()).dataFim(reserva.getDataSaida()).build()));
        reservaRepository.save(reserva);

        ValorReservaDTO valorReservaDTO = ValorReservaDTO
                .builder()
                .dataInicio(reserva.getDataEntrada())
                .dataFim(reserva.getDataSaida())
                .build();

        return new ResponseReserva(ReservaDto
                .builder()
                .id(reserva.getId())
                .dataEntrada(reserva.getDataEntrada())
                .dataSaida(reserva.getDataSaida())
                .valor(reserva.getValor())
                .hospedes(reserva.getHospedes())
                .build());
    }

    public ResponseReserva updateReserva(Long idReserva, RequestReserva requestReserva){
        try{
            Reserva reserva = reservaRepository.getReferenceById(idReserva);

            reserva.setDataEntrada(requestReserva.getDataEntrada());
            reserva.setDataSaida(requestReserva.getDataSaida());
            reserva.setFormaPagamento(requestReserva.getFormaPagamento());
            reservaRepository.save(reserva);

            ValorReservaDTO valorReservaDTO = ValorReservaDTO
                    .builder()
                    .dataInicio(reserva.getDataEntrada())
                    .dataFim(reserva.getDataSaida())
                    .build();

            return new ResponseReserva(ReservaDto
                    .builder()
                    .id(reserva.getId())
                    .formaPagamento(reserva.getFormaPagamento())
                    .dataEntrada(reserva.getDataEntrada())
                    .dataSaida(reserva.getDataSaida())
                    .valor(reserva.getValor())
                    .hospedes(reserva.getHospedes())
                    .build());

        }catch(EntidadeNaoEncontrada e) {
			throw new EntidadeNaoEncontrada("Reserva com ID: " + idReserva + " não encontrado");
		}	
    }
    public void deleteReserva(Long idReserva){
        try {
			reservaRepository.deleteById(idReserva);
		} catch (Exception e) {
			throw new RuntimeException("Reserva com ID: " + idReserva + " não encontrado");
		}
    }

    public ResponseReserva findByIdReserva(Long idReserva){
        Reserva reserva = reservaRepository.findById(idReserva).map(a -> a)
                .orElseThrow(() -> new EntidadeNaoEncontrada("Reserva com ID: " + idReserva + " não encontrado"));

        ValorReservaDTO valorReservaDTO = ValorReservaDTO
                .builder()
                .dataInicio(reserva.getDataEntrada())
                .dataFim(reserva.getDataSaida())
                .build();

         return new ResponseReserva(ReservaDto
                .builder()
                .id(reserva.getId())
                .formaPagamento(reserva.getFormaPagamento())
                .dataEntrada(reserva.getDataEntrada())
                .dataSaida(reserva.getDataSaida())
                .valor(Double.valueOf(valorEstadia(valorReservaDTO)))
                .hospedes(reserva.getHospedes())
                .build());
    }

    public List<ResponseReserva> getAllReserva(){
        try {
            List<Reserva> reservas = reservaRepository.findAll();
            List<ResponseReserva> responseReservas = new ArrayList<>();

            reservas.forEach(reserva -> {
                ValorReservaDTO valorReservaDTO = ValorReservaDTO
                        .builder()
                        .dataInicio(reserva.getDataEntrada())
                        .dataFim(reserva.getDataSaida())
                        .build();

                ResponseReserva responseReserva = new ResponseReserva(ReservaDto
                        .builder()
                        .id(reserva.getId())
                        .formaPagamento(reserva.getFormaPagamento())
                        .dataEntrada(reserva.getDataEntrada())
                        .dataSaida(reserva.getDataSaida())
                        .valor(Double.valueOf(valorEstadia(valorReservaDTO)))
                        .hospedes(reserva.getHospedes())
                        .build());

                responseReservas.add(responseReserva);
            });

            return responseReservas;
        }catch (Exception e){
            throw new ExceptinHandler("erro ao listar as reservas");
        }
    }

    public Double valorEstadia(ValorReservaDTO simular){
        return Double.valueOf(ChronoUnit.DAYS.between(simular.getDataInicio(), simular.getDataFim())*100);
    }
}
