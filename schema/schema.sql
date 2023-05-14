SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Banco de dados: `amy`
--
CREATE DATABASE IF NOT EXISTS `amy` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `amy`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nome` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `nome_UNIQUE` (`nome`)
    ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `categoria`
--

INSERT INTO `categoria` (`id`, `nome`) VALUES(3, 'Cama mesa e banho');
INSERT INTO `categoria` (`id`, `nome`) VALUES(4, 'Eletrônicos');
INSERT INTO `categoria` (`id`, `nome`) VALUES(2, 'Escritório');
INSERT INTO `categoria` (`id`, `nome`) VALUES(1, 'Informatica');

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

DROP TABLE IF EXISTS `produto`;
CREATE TABLE IF NOT EXISTS `produto` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `nome` varchar(300) NOT NULL,
    `descricao` varchar(1000) NOT NULL,
    `preco` double NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `produto`
--

INSERT INTO `produto` (`id`, `nome`, `descricao`, `preco`) VALUES(1, 'Laptop', 'Laptop Gamer', 1000);
INSERT INTO `produto` (`id`, `nome`, `descricao`, `preco`) VALUES(2, 'Caderno', 'Caderno de financas', 15.5);
INSERT INTO `produto` (`id`, `nome`, `descricao`, `preco`) VALUES(3, 'Calculadora', 'Calculadora', 15);
INSERT INTO `produto` (`id`, `nome`, `descricao`, `preco`) VALUES(4, 'Toalha', 'Toalha de banho', 150.5);

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto_categoria`
--

DROP TABLE IF EXISTS `produto_categoria`;
CREATE TABLE IF NOT EXISTS `produto_categoria` (
    `id_produto` int(11) NOT NULL,
    `id_categoria` int(11) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `produto_categoria`
--

INSERT INTO `produto_categoria` (`id_produto`, `id_categoria`) VALUES(1, 1);
INSERT INTO `produto_categoria` (`id_produto`, `id_categoria`) VALUES(2, 2);
INSERT INTO `produto_categoria` (`id_produto`, `id_categoria`) VALUES(3, 4);
INSERT INTO `produto_categoria` (`id_produto`, `id_categoria`) VALUES(4, 3);
