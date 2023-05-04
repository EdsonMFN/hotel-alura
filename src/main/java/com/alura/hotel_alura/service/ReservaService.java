package com.alura.hotel_alura.service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.hotel_alura.entities.Reserva;
import com.alura.hotel_alura.repository.ReservaRepository;
import com.alura.hotel_alura.repository.DTO.ValorReservaDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva insertReserva(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    public Reserva updateReserva(Long idReserva, Reserva newData){
        try{
            Reserva reserva = reservaRepository.getReferenceById(idReserva);
            updateDataReserva(reserva, newData);
            return reservaRepository.save(reserva);

        }catch(EntityNotFoundException e) {
			throw new RuntimeException("Reserva com ID: " + idReserva + " não encontrado");
		}	
    }

    public void updateDataReserva(Reserva reserva, Reserva newData){
        reserva.setDataEntrada(newData.getDataEntrada());
		reserva.setDataSaida(newData.getDataSaida());
		reserva.setFormaPagamento(newData.getFormaPagamento());
		reserva.setHospedes(newData.getHospedes());
    }

    public void deleteReserva(Long idReserva){
        try {
			reservaRepository.deleteById(idReserva);
		} catch (Exception e) {
			throw new RuntimeException("Reserva com ID: " + idReserva + " não encontrado");
		}
    }

    public Reserva findByIdReserva(Long idReserva){
        Optional<Reserva> reservaOpt = reservaRepository.findById(idReserva);
        if(reservaOpt.isPresent() == false){
            throw new RuntimeException("Reserva com ID: " + idReserva + " não encontrado");
        }  
        return reservaOpt.get();
    }

    public List<Reserva> getAllReserva(){
        return reservaRepository.findAll();
    }

    public Long valorEstadia(ValorReservaDTO simular){
        return ChronoUnit.DAYS.between(simular.getDataInicio(), simular.getDataFim())*100;
    }


}
