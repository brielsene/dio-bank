# dio-bank
Banco criado para prática dos conhecimentos aprendidos no Bootcamp Santander pela DIO.

## A Ideia
Como há bootcamps e outras ferramentas incríveis que são pagas, foi pensado em criar um próprio banco da DIO, para ajudar pessoas, que ou não possuem cartão de crédito próprio ou não possuem limite,
a terem uma forma de pagamento mais flex.

## Diagrama de Classes
```mermaid
classDiagram
    class Account {
        - id: Long
        - number: String
        - agency: String
        - balance: float
        - limite: float
        + user: User
    }

    class User {
        - id: Long
        - nome: String
        - senha: String
        - email: String
        + account: Account
        + cards: List<Card>
    }

    class Card {
        - id: Long
        - limite: float
        - flag: String
        + user: User
    }

    class EmailDetails {
        - recipient: String
        - messageBody: String
        - subject: String
        - attachment: String
    }

    Account "1" --o "1" User : Contains
    User "0..1" --o "0..*" Card : Owns



    
   
```
