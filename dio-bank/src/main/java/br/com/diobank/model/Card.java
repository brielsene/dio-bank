package br.com.diobank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codigo;
    private String cvv;

    @Column(name = "data_expiracao")
    private String dataExpiracao;
    private float limite;
    private String flag;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
