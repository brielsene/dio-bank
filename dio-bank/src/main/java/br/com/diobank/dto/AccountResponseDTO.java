package br.com.diobank.dto;

import br.com.diobank.model.Account;

public record AccountResponseDTO(

        /**
         * private Long id;
         *     private String number;
         *     private String agency;
         *     private float balance;
         *     private float limite;
         *     @OneToOne
         *     @JoinColumn(name = "user_id")
         *     private User user;
         */

        Long id,
        String number,
        String agency,
        float balance,
        float limite,

        String nome
) {
    public AccountResponseDTO(Account account){
        this(account.getId(), account.getNumber(), account.getAgency(), account.getBalance(), account.getLimite(), account.getUser().getNome());

    }
}
