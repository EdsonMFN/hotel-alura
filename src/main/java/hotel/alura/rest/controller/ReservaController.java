package hotel.alura.rest.controller;


import java.util.List;

import hotel.alura.domains.entities.Reserva;
import hotel.alura.domains.model.ValorReservaDTO;
import hotel.alura.rest.request.RequestReserva;
import hotel.alura.rest.response.ResponseReserva;
import hotel.alura.service.ReservaService;
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

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    
    @Autowired
    public ReservaService service;

    @PostMapping("/")
    public ResponseEntity<ResponseReserva> insertReserva(@RequestBody RequestReserva requestReserva){
        try{
            ResponseReserva responseReserva = service.insertReserva(requestReserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseReserva);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @PutMapping(value = "/{id}")
	public ResponseEntity<ResponseReserva> updateReserva(@PathVariable Long id, @RequestBody RequestReserva requestReserva) {
		try{
            ResponseReserva responseReserva = service.updateReserva(id, requestReserva);
            return ResponseEntity.ok().body(responseReserva);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
	}

    @DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseReserva> deleteReserva(@PathVariable Long id) {
        try{
            service.deleteReserva(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
	}

    @GetMapping("/")
    public ResponseEntity<List<ResponseReserva>> getListReserva(){
        try{
            return ResponseEntity.ok().body(service.getAllReserva());
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseReserva> findByIdReserva(@PathVariable Long id){
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
