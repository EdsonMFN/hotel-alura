package com.alura.hotel_alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.hotel_alura.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	Usuario findByNomeAndSenha(String nome, String senha);
}
