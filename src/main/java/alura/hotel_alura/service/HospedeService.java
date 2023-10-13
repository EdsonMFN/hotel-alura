package com.alura.hotel_alura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.hotel_alura.entities.Hospede;
import com.alura.hotel_alura.entities.Reserva;
import com.alura.hotel_alura.repository.HospedeRepository;
import com.alura.hotel_alura.repository.ReservaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HospedeService {
    
    @Autowired
    private HospedeRepository hospedeRepository;    

    @Autowired
    private ReservaRepository reservaRepository;

    public Hospede insertHospede(Long idReserva,Hospede hospede){
    	 Optional<Reserva> reservaOpt= reservaRepository.findById(idReserva);

    	 if(reservaOpt.isPresent()) {

    		 Reserva reserva = reservaOpt.get();
             Hospede newHospede = new Hospede( hospede.getNome(), hospede.getSobrenome(), hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone());

    		 newHospede.getReservas().add(reserva);
             hospedeRepository.save(newHospede);

             reserva.getHospedes().add(hospede);
             reservaRepository.save(reserva); 
    		 return newHospede ;
    	 }
    	 throw new RuntimeException("A reserva ID:" + idReserva + " não existe.");
    }

    public Hospede updateHospede(Long idHospede, Hospede newData){
        try{
            Hospede hospede = hospedeRepository.getReferenceById(idHospede);
            updateDataHospede(hospede, newData);
            return hospedeRepository.save(hospede);
        }catch(EntityNotFoundException e) {
			throw new RuntimeException("Hospede com ID: " + idHospede + " não encontrado");
		}	

    }

    public void updateDataHospede(Hospede hospede, Hospede newData){
        hospede.setNome(newData.getNome());
		hospede.setSobrenome(newData.getSobrenome());
		hospede.setTelefone(newData.getTelefone());
		hospede.setDataNascimento(newData.getDataNascimento());
		hospede.setNacionalidade(newData.getNacionalidade());
    }

    public void deleteHospede(Long idHospede){
        try {
			hospedeRepository.deleteById(idHospede);
		} catch (Exception e) {
			throw new RuntimeException("Hospede com ID: " + idHospede + " não encontrado");
		}
    }

    public Hospede findByIdHospede(Long idHospede){
       Optional<Hospede> hospedeOpt = hospedeRepository.findById(idHospede);
        if(hospedeOpt.isPresent() == false){
            throw new RuntimeException("Hospede com ID: " + idHospede + " não encontrado");
        }  
        return hospedeOpt.get();
    }

    public List<Hospede> getAllHospede(){
        return hospedeRepository.findAll();
    }
}
