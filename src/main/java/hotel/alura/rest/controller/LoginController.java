package hotel.alura.rest.controller;



import hotel.alura.domains.entities.Usuario;
import hotel.alura.exception.LoginException;
import hotel.alura.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
