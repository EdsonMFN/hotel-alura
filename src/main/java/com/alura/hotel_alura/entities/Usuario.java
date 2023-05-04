package com.alura.hotel_alura.entities;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id ;
   
    @Column(nullable = false)
    @Nonnull
    private String nome;
    
    @Column(nullable = false)
    @Nonnull
    private String sobrenome;
    
    @Column(nullable = false)
    @Nonnull
    private LocalDate dataNascimento;
    
    @Column(nullable = false)
    @Nonnull
    private String senha;
}
