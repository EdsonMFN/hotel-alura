package com.alura.hotel_alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.hotel_alura.entities.Hospede;

public interface HospedeRepository extends JpaRepository<Hospede,Long>{
    
}
