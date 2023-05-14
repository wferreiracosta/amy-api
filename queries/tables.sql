CREATE TABLE amy.categoria (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX nome_UNIQUE (nome ASC));

CREATE TABLE amy.produto(
    id        INT NOT NULL auto_increment,
    nome      VARCHAR(300) NOT NULL,
    descricao VARCHAR(1000) NOT NULL,
    preco     DOUBLE NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE amy.produto_categoria(
    id_produto   INT NOT NULL,
    id_categoria INT NOT NULL);