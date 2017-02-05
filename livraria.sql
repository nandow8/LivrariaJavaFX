-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 05-Fev-2017 às 23:01
-- Versão do servidor: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `livraria`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `alugueis`
--

CREATE TABLE `alugueis` (
  `id` bigint(20) NOT NULL,
  `autorLivro` varchar(30) DEFAULT NULL,
  `codigoLivro` varchar(30) DEFAULT NULL,
  `disponivel` varchar(15) DEFAULT NULL,
  `distribuidoraLivro` varchar(30) DEFAULT NULL,
  `nomeAluno` varchar(50) DEFAULT NULL,
  `raAluno` varchar(20) DEFAULT NULL,
  `tituloLivro` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `alugueis`
--

INSERT INTO `alugueis` (`id`, `autorLivro`, `codigoLivro`, `disponivel`, `distribuidoraLivro`, `nomeAluno`, `raAluno`, `tituloLivro`) VALUES
(37, 'mamamama', 'a1', 'Alugado', '2222', 'Fernando', '201', 'wewewew');

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

CREATE TABLE `aluno` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `ra` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`id`, `nome`, `ra`) VALUES
(1, 'Fernando', '201'),
(15, 'BLASTOISE', '258'),
(18, 'sasa', '1212'),
(25, 'a', 'a'),
(27, 'b', 'b'),
(31, 'f', 'f');

-- --------------------------------------------------------

--
-- Estrutura da tabela `livro`
--

CREATE TABLE `livro` (
  `id` bigint(20) NOT NULL,
  `autor` varchar(50) DEFAULT NULL,
  `codigodoLivro` varchar(50) DEFAULT NULL,
  `disponivel` varchar(255) DEFAULT NULL,
  `distribuidora` varchar(50) DEFAULT NULL,
  `tituloLivro` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `livro`
--

INSERT INTO `livro` (`id`, `autor`, `codigodoLivro`, `disponivel`, `distribuidora`, `tituloLivro`) VALUES
(2, 'mamamama', 'a1', 'Alugado', '2222', 'wewewew'),
(5, 'fer', 'a2', 'Disponivel', 'ff', 'we2'),
(6, 'Cintiao', 'a3', 'Disponivel', 'sdad', 'guasuda'),
(13, 'qwe', 'a4', 'Disponivel', 'ee', 'qw'),
(16, 'asasas', 'f1', 'Disponivel', 'rtrrtr', 'eweewew'),
(19, 'ddd', 'd2', 'Disponivel', 'ddd', 'ddd'),
(24, 'a', 'a', 'Disponivel', 'a', 'a'),
(28, 'b', 'b', 'Disponivel', 'b', 'b'),
(32, 'f', 'f', 'Disponivel', 'f', 'f');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alugueis`
--
ALTER TABLE `alugueis`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
