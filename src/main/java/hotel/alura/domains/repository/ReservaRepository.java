package hotel.alura.domains.repository;

import hotel.alura.domains.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva,Long>{
    
}
