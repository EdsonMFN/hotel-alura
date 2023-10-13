package hotel.alura.service;


import hotel.alura.domains.entities.Usuario;
import hotel.alura.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotel.alura.domains.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

	public Usuario loginUsuario(Usuario usuario) {
		usuario = usuarioRepository.findByNomeAndSenha(usuario.getNome(),usuario.getSenha());
		if(usuario==null) {
			throw new LoginException("Nome de usuário e senha inválidos");
		}
		return usuario;
	}
}
