package com.alura.hotel_alura.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alura.hotel_alura.entities.Usuario;
import com.alura.hotel_alura.exception.LoginException;
import com.alura.hotel_alura.service.UsuarioService;

@RestController
public class LoginController {
    
    @Autowired
    public UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Usuario> loginUsuario(@RequestBody Usuario usuario){
        try{
        	usuario = usuarioService.loginUsuario(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }catch(LoginException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }catch(Exception e){
            String message = "Error: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }
}
