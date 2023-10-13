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
@Table(name = "hospede")
public class Hospede {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hospede")
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id ;
   
    @Column(name = "nome",nullable = false)
    @Nonnull
    private String nome;
    
    @Column(name = "sobrenome",nullable = false)
    @Nonnull
    private String sobrenome;
    
    @Column(name = "dataNascimento",nullable = false)
    @Nonnull
    private LocalDate dataNascimento;
    
    @Column(name = "nacionalidade",nullable = false)
    @Nonnull
    private String nacionalidade;
    
    @Column(name = "telefone",nullable = false)
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
