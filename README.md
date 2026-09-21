# Conta Bancária API

API RESTful desenvolvida como desafio técnico para estágio em Desenvolvimento Back-End.

O projeto simula o gerenciamento de contas bancárias de uma cooperativa de crédito, permitindo cadastrar correntistas, abrir contas, realizar depósitos e saques, consultar extratos e aplicar regras específicas para conta corrente e conta poupança.

## Tecnologias utilizadas

- Java 8
- Spring Boot 2.7.18
- Maven
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Swagger / OpenAPI
- JUnit 5

## Funcionalidades implementadas

- Cadastro de correntistas
- Consulta de correntistas
- Abertura de conta corrente
- Abertura de conta poupança
- Consulta de contas
- Depósito
- Saque
- Registro de transações
- Extrato da conta
- Tratamento padronizado de erros
- Rendimento de conta poupança
- Juros sobre saldo negativo da conta corrente
- Documentação com Swagger
- Testes unitários das principais regras de negócio

## Regras de negócio

### Conta Corrente

A conta corrente possui um limite adicional.

O valor máximo disponível para saque é:

saldo + limite

Exemplo:

Saldo: R$ 500,00  
Limite: R$ 200,00  
Disponível para saque: R$ 700,00

Caso o saque ultrapasse o valor disponível, a API retorna erro `400 Bad Request`.

### Conta Poupança

A conta poupança não possui limite.

O saque só pode ser realizado se houver saldo suficiente.

Caso o valor solicitado seja maior que o saldo, a API retorna erro `400 Bad Request`.

### Depósito

O depósito:

- aumenta o saldo da conta;
- registra uma transação do tipo `DEPOSITO`.

### Saque

O saque:

- aplica a regra correspondente ao tipo da conta;
- reduz o saldo;
- registra uma transação do tipo `SAQUE`.

### Rendimento da Poupança

O rendimento é aplicado sobre o saldo atual da conta poupança.

Exemplo:

Saldo: R$ 1.000,00  
Taxa: 0.01  
Rendimento: R$ 10,00  
Novo saldo: R$ 1.010,00

A operação registra uma transação do tipo `RENDIMENTO`.

### Juros da Conta Corrente

Os juros são aplicados apenas quando o saldo da conta corrente está negativo.

Exemplo:

Saldo: -R$ 200,00  
Taxa: 0.10  
Juros: R$ 20,00  
Novo saldo: -R$ 220,00

A operação registra uma transação do tipo `JUROS`.

## Estrutura do projeto

```text
src
├── main
│   ├── java
│   │   └── com
│   │       └── pactomais
│   │           └── contabancariaapi
│   │               ├── config
│   │               ├── controller
│   │               ├── dto
│   │               ├── exception
│   │               ├── model
│   │               ├── repository
│   │               ├── service
│   │               └── ContaBancariaApiApplication.java
│   │
│   └── resources
│
└── test
    └── java
        └── com
            └── pactomais
                └── contabancariaapi
                    └── model