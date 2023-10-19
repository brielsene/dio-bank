package br.com.diobank.service;

import br.com.diobank.model.Card;
import br.com.diobank.model.User;
import br.com.diobank.repository.CardRepository;
import br.com.diobank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class CardService {

    private Random random = new Random();

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    public void newCard(Long id){
        Card card = new Card();
        gerarCodigoCartaoDeCredito(card);
        gerarCVV(card);
        gerarDataDeExpiracao(card);
        card.setFlag("Master-Card");
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário com ID inexistente"));
                card.setUser(user);
        cardRepository.save(card);



    }

    private void gerarCodigoCartaoDeCredito(Card card){
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 16; i++){
            int digito = random.nextInt(10);
            codigo.append(digito);
        }

        card.setCodigo(codigo.toString());

    }

    private void gerarCVV(Card card){
        StringBuilder codigo = new StringBuilder();


        for (int i = 0; i < 3; i++){
            int digito = random.nextInt(10);
            codigo.append(digito);
        }

        card.setCvv(codigo.toString());


    }

    private void gerarDataDeExpiracao(Card card){
        LocalDate hoje = LocalDate.now();

        int anoMin = hoje.getYear()+3;

        int anoMax = hoje.getYear()+8;

        int anoAleatorio = random.nextInt(anoMax - anoMin + 1) + anoMin;

        int mesAleatorio = random.nextInt(12)+1;

        LocalDate localDate = LocalDate.of(anoAleatorio, mesAleatorio, 1);
        String data = localDate.toString();
        String [] dataFormat = data.split("-");
//        for (int i = 0; i < 3; i++){
//
//        }

        StringBuilder dataFinish = new StringBuilder();

        String anoFinish = dataFormat[0].substring(2);

        dataFinish.append(dataFormat[1]+"/"+anoFinish);

        card.setDataExpiracao(dataFinish.toString());
    }
}
