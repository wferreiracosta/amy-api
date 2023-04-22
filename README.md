# amy-api

*O Nome "Amy" vem do personagem Amy Sosa da série de televisão [Superstore](https://pt.wikipedia.org/wiki/Superstore).*

API de e-commerce feito com Spring Boot com as principais atividades de uma loja. <br>

## Especificações
Java 11 <br>
Spring 2.7.11 <br>


## Banco de dados

Existe um arquivo Docker com container de Mysql e PhPMyAdmin dentro do projeto para serem utilizados nos testes da API. Para utiliza-lo basta executar o comando abaixo dentro do diretorio do projeto:

<code>docker compose -f "docker\amy-database\docker-compose.yml" up -d --build</code>