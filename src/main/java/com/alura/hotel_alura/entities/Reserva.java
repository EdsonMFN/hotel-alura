package com.alura.hotel_alura.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id ;

    @Column(nullable = false)
    @Nonnull
    private LocalDate dataEntrada;

    @Column
    private LocalDate dataSaida;

    @Column(nullable = false)
    @Nonnull
    private Double valor;

    @Column(nullable = false)
    @Nonnull
    private String formaPagamento;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reserva_hospede",
        joinColumns = @JoinColumn(name = "reserva_id"),
        inverseJoinColumns = @JoinColumn(name = "hospede_id"))
        @JsonIgnore
    private List<Hospede> hospedes;

	public Reserva(LocalDate dataEntrada, LocalDate dataSaida, Double valor, String formaPagamento) {
		super();
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
		this.hospedes =  new ArrayList<Hospede>();
	}
    
    
}
