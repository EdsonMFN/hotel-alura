package com.alura.hotel_alura.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alura.hotel_alura.entities.Reserva;
import com.alura.hotel_alura.repository.DTO.ValorReservaDTO;
import com.alura.hotel_alura.service.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    public ReservaService service;

    @PostMapping("/")
    public ResponseEntity<Reserva> insertReserva(@RequestBody Reserva reserva){
        try{
            reserva = service.insertReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @PutMapping(value = "/{id}")
	public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva newData) {
		try{
            newData = service.updateReserva(id, newData);
            return ResponseEntity.ok().body(newData);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
	}

    @DeleteMapping(value = "/{id}")
	public ResponseEntity<Reserva> deleteReserva(@PathVariable Long id) {
        try{
            service.deleteReserva(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
	}

    @GetMapping("/")
    public ResponseEntity<List<Reserva>> getListReserva(){
        try{
            return ResponseEntity.ok().body(service.getAllReserva());
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findByIdReserva(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(service.findByIdReserva(id));
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }
    
    @PostMapping("/simular-valor")
    public Long simularEstadia(@RequestBody ValorReservaDTO valores){
        return service.valorEstadia(valores);
    } 
}
