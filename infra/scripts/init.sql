\c catalogo_filmes

CREATE TABLE IF NOT EXISTS diretores
(
    id_diretor   SERIAL PRIMARY KEY,
    nome_diretor VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS filmes
(
    id_filme             SERIAL PRIMARY KEY,
    nome_filme           VARCHAR NOT NULL,
    duracao_total_min    INT     NOT NULL,
    avaliacao_filme      NUMERIC(2, 1),
    ano_lancamento_filme INT     NOT NULL,
    orcamento_filme BIGINT NOT NULL,
    descricao TEXT,
    diretor_filme INT NOT NULL,
    FOREIGN KEY (diretor_filme) REFERENCES diretores(id_diretor)
);

CREATE TABLE IF NOT EXISTS atores
(
    id_ator   SERIAL PRIMARY KEY,
    nome_ator VARCHAR NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS filme_ator
(
    id_filme_ator SERIAL PRIMARY KEY,
    id_filme      INT NOT NULL,
    id_ator       INT NOT NULL,
    FOREIGN KEY (id_filme) REFERENCES filmes (id_filme),
    FOREIGN KEY (id_ator) REFERENCES atores (id_ator)
);



