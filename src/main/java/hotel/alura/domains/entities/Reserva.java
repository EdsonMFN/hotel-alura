package hotel.alura.domains.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id ;

    @Column(name = "data_entrada",nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "data_saida",nullable = false)
    private LocalDate dataSaida;

    @Column(name = "valor",nullable = false)
    @Nonnull
    private Double valor;

    @Column(name = "forma_pagamento",nullable = false)
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
