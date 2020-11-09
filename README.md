# Finance transaction
Projeto de transação financeira

# Features
* Cadastro de conta
* Consulta de contas
* Cadastro de transação

# Requisitos
* [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html);
* [Docker](https://www.docker.com/);
* [Lombok](https://projectlombok.org/)

# Execução
_Os comandos a baixo são baseados nos Sistemas Operacionais Linux e macOS._
### Iniciar o banco de dados
```bash
sudo docker-compose up
```
### Compilar e testar
```bash
mvn clean install
```
### Executar a aplicação
```bash
mvn spring-boot:run
```

### Features
* [Create Account](documentation/account-create.md) : `POST /accounts`
* [Find Account](documentation/account-find.md) : `GET /accounts/{accountId}`
* [Create Transaction](documentation/transaction-create.md) : `POST /transactions`

### Monitoração
[Hystrix](http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Factuator%2Fhystrix.stream)

## Stack
[![Java](https://img.shields.io/badge/Java-11-blue)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-Greenwich.SR3-green)](https://spring.io/blog/2019/09/12/spring-cloud-greenwich-sr3-released)
[![Maven](https://img.shields.io/badge/Maven-3.3-red)](https://www.postgresql.org/docs/12/release-12-4.html)
[![Postgres](https://img.shields.io/badge/Postgres-12.4-green)](https://www.postgresql.org/docs/12/release-12-4.html)

## Pontos de melhoria
~~* Substituir o id numérico por UUID e utiliar um código sequencial como id da conta;~~
