package br.com.diobank.controller;

import br.com.diobank.dto.AccountResponseDTO;
import br.com.diobank.dto.SignupRequestDto;
import br.com.diobank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity insertNewAccount(@RequestBody SignupRequestDto datas){
        accountService.cadastrar(datas);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>>getAllAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAcounts());
    }
}
