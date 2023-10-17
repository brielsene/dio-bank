package br.com.diobank.service;

import br.com.diobank.dto.AccountResponseDTO;
import br.com.diobank.dto.SignupRequestDto;
import br.com.diobank.model.Account;
import br.com.diobank.model.EmailDetails;
import br.com.diobank.model.User;
import br.com.diobank.repository.AccountRepository;
import br.com.diobank.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    private Random random = new Random();
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailService emailService;


    @Autowired
    private UserRepository userRepository;

    public void cadastrar(SignupRequestDto dados) throws MessagingException {
        Account account = getAccount();
        User user = new User(null, dados.nome(), dados.senha(), dados.email(), account, null );
        account.setUser(user);
        userRepository.save(user);
        //
        enviarEmail(dados, account);
        //
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSubject("Criação da conta no Dio-Bank");
        emailDetails.setRecipient(dados.email());
        emailDetails.setMessageBody("Olá "+ dados.nome()+", conta criado com sucesso! \n" +"Agência: "+ account.getAgency()+". \n"+
                "Número da conta: "+ account.getNumber()+"\n \n"+" Qualquer dúvida entrar em contato com a DIO. \n"+
                "Atenciosamente, DIO, DIO-BANK");




        emailService.sendEmailWithAttachment(emailDetails);

    }

    private void enviarEmail(SignupRequestDto dados, Account account) {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSubject("Criação da conta no Dio-Bank");
        emailDetails.setRecipient(dados.email());
        emailDetails.setMessageBody("Olá "+ dados.nome()+", conta criado com sucesso! \n" +"Agência: "+ account.getAgency()+". \n"+
                "Número da conta: "+ account.getNumber()+"\n \n"+" Qualquer dúvida entrar em contato com a DIO. \n"+
                "Atenciosamente, DIO, DIO-BANK");
        emailService.sendEmail(emailDetails);
    }

    private Account getAccount() {
        Account account = new Account();
        account.setBalance(0);
        int numeroAleatorio = random.nextInt(9000) + 1000;
        String numeroFormatado = String.format("%04d", numeroAleatorio);
        account.setAgency(numeroFormatado);
        numeroAleatorio = random.nextInt(90000000) + 10000000;
        numeroFormatado = String.format("%08d", numeroAleatorio);
        account.setNumber(numeroFormatado);
        return account;
    }

    ;

    public List<AccountResponseDTO>getAllAcounts(){
        List<Account> all = accountRepository.findAll();
        return all.stream().map(AccountResponseDTO::new).toList();
    }

    public AccountResponseDTO getById(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Conta com este id não encontrada"));

        AccountResponseDTO accountResponseDTO = new AccountResponseDTO(account);
        return accountResponseDTO;

    }

    public void atualizarSenha(Long id, SignupRequestDto dados){
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário inexistente com id: "+id));
        user.setSenha(dados.senha());
        userRepository.save(user);
    }

    public void deletarConta(Long id){
        userRepository.deleteById(id);
    }
}


