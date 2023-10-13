package hotel.alura.domains.entities;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
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
