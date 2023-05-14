-- CATEGORIA
INSERT INTO amy.categoria (nome) VALUES ('Informatica');
INSERT INTO amy.categoria (nome) VALUES ('Escritório');
INSERT INTO amy.categoria (nome) VALUES ('Cama mesa e banho');
INSERT INTO amy.categoria (nome) VALUES ('Eletrônicos');
-- PRODUTO
INSERT INTO amy.produto(nome, descricao, preco) VALUES ('Laptop','Laptop Gamer',1000);
INSERT INTO amy.produto(nome, descricao, preco) VALUES ('Caderno','Caderno de financas',15.50);
INSERT INTO amy.produto(nome, descricao, preco) VALUES ('Calculadora','Calculadora',15);
INSERT INTO amy.produto(nome, descricao, preco) VALUES ('Toalha','Toalha de banho',150.50);
-- PRODUTO_CATEGORIA
INSERT INTO amy.produto_categoria(id_produto, id_categoria) VALUES (1,1);
INSERT INTO amy.produto_categoria(id_produto, id_categoria) VALUES (2,2);
INSERT INTO amy.produto_categoria(id_produto, id_categoria) VALUES (3,4);
INSERT INTO amy.produto_categoria(id_produto, id_categoria) VALUES (4,3);