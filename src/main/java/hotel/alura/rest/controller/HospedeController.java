package hotel.alura.rest.controller;


import java.util.List;

import hotel.alura.rest.request.RequestHospede;
import hotel.alura.rest.response.ResponseHospede;
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

import hotel.alura.domains.entities.Hospede;
import hotel.alura.service.HospedeService;

@RestController
@RequestMapping("/hospede")
public class HospedeController {
    
    @Autowired
    public HospedeService hospedeService;
    
    @PostMapping("/{idReserva}")
    public ResponseEntity<ResponseHospede> insertHospede(@PathVariable Long idReserva, @RequestBody RequestHospede requestHospede){
        try{
            hospede = hospedeService.insertHospede(idReserva,hospede);
            return ResponseEntity.status(HttpStatus.CREATED).body(hospede);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @PutMapping(value = "/{id}")
	public ResponseEntity<Hospede> updateHospede(@PathVariable Long id, @RequestBody Hospede newData) {
		try{
            newData = hospedeService.updateHospede(id, newData);
            return ResponseEntity.ok().body(newData);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
	}

    @DeleteMapping(value = "/{id}")
	public ResponseEntity<Hospede> deleteHospede(@PathVariable Long id) {
        try{
            hospedeService.deleteHospede(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
	}

    @GetMapping("/")
    public ResponseEntity<List<Hospede>> getListHospedes(){
        try{
            return ResponseEntity.ok().body(hospedeService.getAllHospede());
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospede> findByIdHospedes(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(hospedeService.findByIdHospede(id));
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }
}
