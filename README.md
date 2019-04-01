# Projeto Provider Mock

  Este projeto faz parte da prova de conceito da conclusão do curso de pós-graduação em Arquitetura de Sistemas Distribuídos. Ele contempla o desenvolvimento de uma aplicação mock para fornecedores.

## Objetivo

Este projeto permite a interação com marketplace-backend via WebService SOA de forma mais agradável ao usuário apresentando uma camada de requisição REST com Swagger. Porém pode ser substituído por qualquer aplicação de comunicação SOA.

## Tecnologias Utilizadas

* Git
* Java 8
* Spring Boot
* Spring Web Services
* Lombok
* SpringFox Swagger
* Docker
* Docker Compose

## Pré Requisitos para executar o projeto

  A máquina deve possuir git, docker e docker compose instalados e o projeto [marketplace-backend](https://github.com/viniciusufop/marketplace-backend) deve estar executando.

## Executando o projeto

* Efetue o clone do projeto através do comando: ```git clone https://github.com/viniciusufop/provider-mock.git```
* Acesse a pasta provider-mock criada.
* Execute o comando ```docker-compose up -d```
* O Docker vai subir o container do projeto.

## Estrutura de containers do projeto

  O arquivo docker-compose está configurado para utilizar a network abaixo:

* net-backend 

  O arquivo docker-compose está configurado para subir o container abaixo:

* Container da aplicação provider-mock exposto na porta 9001 (associado net-backend)
