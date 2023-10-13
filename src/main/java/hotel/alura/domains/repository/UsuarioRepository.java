package hotel.alura.domains.repository;

import hotel.alura.domains.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	Usuario findByNomeAndSenha(String nome, String senha);
}
