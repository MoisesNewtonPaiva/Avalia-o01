package br.com.empresa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String nome; 
    private String email; 
    private LocalDate dataAdmissao; 

    @ManyToOne 
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;
}
