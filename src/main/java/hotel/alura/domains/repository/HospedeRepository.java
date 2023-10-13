package hotel.alura.domains.repository;

import hotel.alura.domains.entities.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospedeRepository extends JpaRepository<Hospede,Long>{
    
}
