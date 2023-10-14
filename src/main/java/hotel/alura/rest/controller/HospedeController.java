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
    
    @PostMapping("/reserva/{idReserva}")
    public ResponseEntity<ResponseHospede> insertHospede(@PathVariable Long idReserva, @RequestBody RequestHospede requestHospede){
        try{
            ResponseHospede responseHospede = hospedeService.insertHospede(idReserva,requestHospede);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseHospede);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @PutMapping(value = "/{idHospede}")
	public ResponseEntity<ResponseHospede> updateHospede(@PathVariable Long idHospede, @RequestBody RequestHospede requestHospede) {
		try{
            ResponseHospede responseHospede = hospedeService.updateHospede(idHospede, requestHospede);
            return ResponseEntity.ok().body(responseHospede);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
	}

    @DeleteMapping(value = "/{idHospede}")
	public ResponseEntity<ResponseHospede> deleteHospede(@PathVariable Long idHospede) {
        try{
            hospedeService.deleteHospede(idHospede);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
	}

    @GetMapping("/")
    public ResponseEntity<List<ResponseHospede>> getListHospedes(){
        try{
            List<ResponseHospede> responseHospedes = hospedeService.getAllHospede();
            return ResponseEntity.ok().body(responseHospedes);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

    @GetMapping("/{idHospede}")
    public ResponseEntity<ResponseHospede> findByIdHospedes(@PathVariable Long idHospede){
        try{
            ResponseHospede responseHospede = hospedeService.findByIdHospede(idHospede);
            return ResponseEntity.ok().body(responseHospede);
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }
}
