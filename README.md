# amy-api

*O Nome "Amy" vem do personagem Amy Sosa da série de televisão [Superstore](https://pt.wikipedia.org/wiki/Superstore).*

API de e-commerce feito com Spring Boot com as principais atividades de uma loja. <br>

## Docker

Existe um arquivo Docker com container de Mysql e PhpMyAdmin dentro do projeto para serem utilizados nos testes e desenvolvimento da API. 
Para utiliza-lo basta executar o comando abaixo dentro do diretorio do projeto:

<code>docker compose -f "docker\amy-database\docker-compose.yml" up -d --build</code>

### PhpMyAdmin

Para acessar o PhpMyAdmin basta usar a seguinte URL e dados no navegador
```
http://localhost/
```

Credenciais:
```
Banco: mysql
Login: root
Senha: password
```

## Endpoints

<table>
  <tr>
    <td colspan="2">Categoria</td>
  </tr>
  <tr><td>One</td><td>Two</td></tr>
  <tr><td>One</td><td>Two</td></tr>
  <tr><td>One</td><td>Two</td></tr>
  <tr>
    <td colspan="2">Produto</td>
  </tr>
  <tr><td>One</td><td>Two</td></tr>
  <tr><td>One</td><td>Two</td></tr>
</table>