package com.alura.hotel_alura.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.hotel_alura.entities.Usuario;
import com.alura.hotel_alura.exception.LoginException;
import com.alura.hotel_alura.repository.UsuarioRepository;

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
