package com.alura.hotel_alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.hotel_alura.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva,Long>{
    
}
