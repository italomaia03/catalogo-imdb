-- Filme Intersterllar

-- Inserir atores
INSERT INTO atores (nome_ator)
VALUES ('Matthew McConaughey')
ON CONFLICT (nome_ator) DO NOTHING;

INSERT INTO atores (nome_ator)
VALUES ('Anne Hathaway')
ON CONFLICT (nome_ator) DO NOTHING;

-- Inserir diretor
INSERT INTO diretores (nome_diretor)
VALUES ('Christopher Nolan')
ON CONFLICT (nome_diretor) DO NOTHING;

-- Inserir filme
INSERT INTO filmes (nome_filme, duracao_total_min, avaliacao_filme, ano_lancamento_filme)
VALUES ('Interstellar', 169, 8.6, 2014);

-- Relacionar atores ao filme
INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Interstellar'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Matthew McConaughey'));

INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Interstellar'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Anne Hathaway'));

-- Relacionar diretor ao filme
INSERT INTO filme_diretor (id_filme, id_diretor)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Interstellar'),
        (SELECT id_diretor FROM diretores WHERE nome_diretor = 'Christopher Nolan'));

-- -------------------------------------------------------------------------------------------
-- Filme The Shawnshank Redemption
-- Inserir atores
INSERT INTO atores (nome_ator)
VALUES ('Tim Robbins')
ON CONFLICT (nome_ator) DO NOTHING;

INSERT INTO atores (nome_ator)
VALUES ('Morgan Freeman')
ON CONFLICT (nome_ator) DO NOTHING;

-- Inserir diretor
INSERT INTO diretores (nome_diretor)
VALUES ('Frank Darabont')
ON CONFLICT (nome_diretor) DO NOTHING;

-- Inserir filme
INSERT INTO filmes (nome_filme, duracao_total_min, avaliacao_filme, ano_lancamento_filme)
VALUES ('The Shawshank Redemption', 142, 9.3, 1994);

-- Relacionar atores ao filme
INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'The Shawshank Redemption'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Tim Robbins'));

INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'The Shawshank Redemption'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Morgan Freeman'));

-- Relacionar diretor ao filme
INSERT INTO filme_diretor (id_filme, id_diretor)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'The Shawshank Redemption'),
        (SELECT id_diretor FROM diretores WHERE nome_diretor = 'Frank Darabont'));
-- ------------------------------------------------------------------------------------------

-- Filme Inception

-- Inserir filme
INSERT INTO filmes (nome_filme, duracao_total_min, avaliacao_filme, ano_lancamento_filme)
VALUES ('Inception', 148, 8.8, 2010);

-- Inserir atores
INSERT INTO atores (nome_ator)
VALUES ('Leonardo DiCaprio')
ON CONFLICT (nome_ator) DO NOTHING;

INSERT INTO atores (nome_ator)
VALUES ('Ellen Page')
ON CONFLICT (nome_ator) DO NOTHING;

-- Inserir diretor
INSERT INTO diretores (nome_diretor)
VALUES ('Christopher Nolan')
ON CONFLICT (nome_diretor) DO NOTHING;

-- Relacionar atores ao filme
INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Inception'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Leonardo DiCaprio'));

INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Inception'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Ellen Page'));

-- Relacionar diretor ao filme
INSERT INTO filme_diretor (id_filme, id_diretor)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Inception'),
        (SELECT id_diretor FROM diretores WHERE nome_diretor = 'Christopher Nolan'));

-- Filme Pulp Fiction

-- Inserir filme Pulp Fiction
INSERT INTO filmes (nome_filme, duracao_total_min, avaliacao_filme, ano_lancamento_filme)
VALUES ('Pulp Fiction', 154, 8.9, 1994);

-- Inserir atores
INSERT INTO atores (nome_ator)
VALUES ('John Travolta')
ON CONFLICT (nome_ator) DO NOTHING;

INSERT INTO atores (nome_ator)
VALUES ('Uma Thurman')
ON CONFLICT (nome_ator) DO NOTHING;

INSERT INTO atores (nome_ator)
VALUES ('Samuel L. Jackson')
ON CONFLICT (nome_ator) DO NOTHING;

-- Inserir diretor se não estiver presente
INSERT INTO diretores (nome_diretor)
VALUES ('Quentin Tarantino')
ON CONFLICT (nome_diretor) DO NOTHING;

-- Relacionar diretor ao filme Pulp Fiction
INSERT INTO filme_diretor (id_filme, id_diretor)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Pulp Fiction'),
        (SELECT id_diretor FROM diretores WHERE nome_diretor = 'Quentin Tarantino'));

-- Relacionar atores ao filme Pulp Fiction
INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Pulp Fiction'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'John Travolta'));

INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Pulp Fiction'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Uma Thurman'));

INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Pulp Fiction'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Samuel L. Jackson'));

-- Filme Kill Bill: Volume 1

-- Inserir filme Kill Bill: Volume 1
INSERT INTO filmes (nome_filme, duracao_total_min, avaliacao_filme, ano_lancamento_filme)
VALUES ('Kill Bill: Volume 1', 111, 8.2, 2003);

-- Inserir atores
INSERT INTO atores (nome_ator)
VALUES ('Lucy Liu')
ON CONFLICT (nome_ator) DO NOTHING;

INSERT INTO atores (nome_ator)
VALUES ('Vivica A. Fox')
ON CONFLICT (nome_ator) DO NOTHING;

-- Inserir diretor se não estiver presente
INSERT INTO diretores (nome_diretor)
VALUES ('Quentin Tarantino')
ON CONFLICT (nome_diretor) DO NOTHING;

-- Relacionar diretor ao filme Kill Bill: Volume 1
INSERT INTO filme_diretor (id_filme, id_diretor)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Kill Bill: Volume 1'),
        (SELECT id_diretor FROM diretores WHERE nome_diretor = 'Quentin Tarantino'));

-- Relacionar atores ao filme Kill Bill: Volume 1
INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Kill Bill: Volume 1'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Uma Thurman'));

INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Kill Bill: Volume 1'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Lucy Liu'));

INSERT INTO filme_ator (id_filme, id_ator)
VALUES ((SELECT id_filme FROM filmes WHERE nome_filme = 'Kill Bill: Volume 1'),
        (SELECT id_ator FROM atores WHERE nome_ator = 'Vivica A. Fox'));
