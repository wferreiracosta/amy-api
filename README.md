# amy-api

## Banco de dados

Existe um arquivo Docker com container de Mysql e PhPMyAdmin dentro do projeto para serem utilizados nos testes da API. Para utiliza-lo basta executar o comando abaixo dentro do diretorio do projeto:

<code>docker compose -f "docker\amy-database\docker-compose.yml" up -d --build</code>