package br.com.diobank.controller;

import br.com.diobank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;
    @PostMapping("/{id}")
    public ResponseEntity newCard(@PathVariable("id")Long id){
        cardService.newCard(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
