package br.com.diobank.controller;

import br.com.diobank.dto.SignupRequestDto;
import br.com.diobank.model.User;
import br.com.diobank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;

//    @PostMapping
//    public ResponseEntity insert(@RequestBody SignupRequestDto dados){
//        User user = new User(null, dados.nome(), dados.senha(), null, null);
//        repository.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
