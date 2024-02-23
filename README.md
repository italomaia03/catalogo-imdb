# CATÁLOGO DE FILMES - Santander Coders - Módulo de POO I

## Introdução

Bem-vindo à documentação da API desenvolvida utilizando Spring Boot 3.2.2. Esta API oferece endpoints para gerenciar informações sobre filmes, atores e diretores. Utiliza Java 21 como linguagem principal, Docker Engine 25.0.3 para contêinerização e container PostgreSQL 16.2 como banco de dados.

## Tecnologias Utilizadas

- Spring Boot 3.2.2
- Java 21
- Docker Engine 25.0.3
- Docker Compose 2.24.45
- PostgreSQL 16.2 - Alpine 3.19

## Configuração do Ambiente

Certifique-se de ter o Docker Engine e o Docker Compose instalados em seu ambiente de desenvolvimento antes de prosseguir. Também é necessário configurar o banco de dados PostgreSQL com as credenciais apropriadas.

## Configuração da Base de Dados

A aplicação utiliza o PostgreSQL como banco de dados. Certifique-se de renomear o aquivo .env-example para .env e fornecer as credenciais do seu banco de dados.

```
POSTGRES_USER=
POSTGRES_PASSWORD=
POSTGRES_DB=
POSTGRES_HOST_PORT=
POSTGRES_DOCKER_PORT=
```
## Executando a Aplicação com Docker Compose

Para facilitar o gerenciamento dos contêineres da aplicação, você pode utilizar o Docker Compose. Para rodar a aplicação, basta ir para o diretório da aplicação e rodar o docker compose:

```bash
cd ~/local_da_aplicação
docker compose up -d --build
```
## Endpoints

### Filmes

- **GET /api/v1/filmes:** Retorna todos os filmes cadastrados.
- **GET /api/v1/filmes?nomeFilme=:** Retorna filmes com base no nome fornecido como parâmetro.
- **POST /api/v1/filmes:** Adiciona um novo filme. Requer um corpo JSON válido.
- **PATCH /api/v1/filmes/{idFilme}:** Atualiza parcialmente as informações de um filme existente com base no ID fornecido.

### Atores

- **GET /api/v1/atores:** Retorna todos os atores cadastrados.
- **POST /api/v1/atores:** Adiciona um novo ator. Requer um corpo JSON válido.

### Diretores

- **GET /api/v1/diretores:** Retorna todos os diretores cadastrados.
- **POST /api/v1/diretores:** Adiciona um novo diretor. Requer um corpo JSON válido.

## Exemplos de Requisições

### Exemplo de POST para /api/v1/filmes

Os campos "descricao", "diretorFilme" e "atoresFilmes" são opcionais, os demais são obrigatórios

#### Exemplos de corpos válidos para a requisição

```json
{
  "nomeFilme": "Dune",
  "duracaoTotalMinutos" : "166",
  "avaliacaoFilme" : "8.5",
  "anoLancamentoFilme" : "2019",
  "orcamentoFilme" : "122000000",
  "descricao" : "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he must prevent a terrible future only he can foresee.",
  "diretorFilme" : {
    "nome" : "Denis Villeneuve"
  },
  "atoresFilme" : [
    {
      "nome" : "Timothée Chalamet"
    },
    {
      "nome" : "Zendaya"
    },
    {
      "nome" : "Austin Butler"
    },
    {
      "nome" : "Florence Pugh"
    }
  ]
}
```
```json
{
  "nomeFilme": "Dune",
  "duracaoTotalMinutos" : "166",
  "avaliacaoFilme" : "8.5",
  "anoLancamentoFilme" : "2019",
  "orcamentoFilme" : "122000000"
}
```
```json
{
  "nomeFilme": "Dune",
  "duracaoTotalMinutos" : "166",
  "avaliacaoFilme" : "8.5",
  "anoLancamentoFilme" : "2019",
  "orcamentoFilme" : "122000000",
  "descricao" : "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he must prevent a terrible future only he can foresee."
}
```

Qualquer variação que envolva a retirada de um dos atributos opcionais previamente citados é válida.

#### Exemplo de resposta 201 CREATED
A API processaria essa solicitação e, se tudo estiver correto, poderia retornar uma resposta como esta com um status HTTP 201 (Created):
```json
{
  "idFilme": 12,
  "nomeFilme": "Dune",
  "duracaoTotalMinutos" : "166",
  "avaliacaoFilme" : "8.5",
  "anoLancamentoFilme" : "2019",
  "orcamentoFilme" : "122000000",
  "descricao" : "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he must prevent a terrible future only he can foresee.",
  "diretorFilme" : {
    "nome" : "Denis Villeneuve"
  },
  "atoresFilme" : [
    {
      "nome" : "Timothée Chalamet"
    },
    {
      "nome" : "Zendaya"
    },
    {
      "nome" : "Austin Butler"
    },
    {
      "nome" : "Florence Pugh"
    }
  ]
}
```
```json
{
  "idFilme": 7,
  "nomeFilme": "Dune",
  "duracaoTotalMinutos": 166,
  "avaliacaoFilme": 8.5,
  "anoLancamentoFilme": 2019,
  "orcamentoFilme": 122000000,
  "descricao": "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he must prevent a terrible future only he can foresee.",
  "diretorFilme": null,
  "atoresFilme": null
}
```
#### Exemplo de erro 400 BAD REQUEST
Caso um dos campos obrigatórios não seja informado, a aplicação retorna um BAD REQUEST (código 400) infomando o campo faltante.
```json
{
  "duracaoTotalMinutos" : "166",
  "avaliacaoFilme" : "8.5",
  "anoLancamentoFilme" : "2019",
  "orcamentoFilme" : "122000000",
  "descricao" : "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he must prevent a terrible future only he can foresee."
}
```
```json
{
  "Erro": "O campo a seguir não pode ser nulo: nomeFilme"
}
```
### Exemplo de POST para /api/v1/atores e para /api/v1/atores
O corpo para ator só pede o atributo nome. É obrigatório informar o nome do ator. Caso o atributo não seja informado, ou seja informado vazio, um BAD REQUEST (Código 400) será enviado como resposta.
#### Exemplo de corpo válido para a requisição
```json
{
"nome": "Kayk Brito"
}
```
#### Exemplo de Resposta código 201 CREATED
```json
{
    "idAtor": 16,
    "nomeAtor": "Kayk Brito"
}
```
#### Exemplo de erro código 400 BAD REQUEST
```json
{
  "nome": ""
}
```
```json
{
  "Erro": "O campo a seguir não pode ser nulo: nome"
}
```

