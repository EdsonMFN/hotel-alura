package com.alura.hotel_alura.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hospede {
    
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
    private String nacionalidade;
    
    @Column(nullable = false)
    @Nonnull
    private String telefone;

    @ManyToMany(mappedBy = "hospedes")
    private List<Reserva> reservas;

	public Hospede(String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
		this.reservas = new ArrayList<Reserva>();
	}
    
    
}
