package br.com.diobank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float limite;
    private String flag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
