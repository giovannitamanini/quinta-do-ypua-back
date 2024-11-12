CREATE DATABASE IF NOT EXISTS pousada;

USE pousada;

CREATE TABLE IF NOT EXISTS usuario (
  id int(11) NOT NULL AUTO_INCREMENT,
  usuario varchar(45) NOT NULL,
  senha varchar(64) NOT NULL,
  ativo tinyint(4) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS permissao (
  id int(11) NOT NULL AUTO_INCREMENT,
  nome varchar(45) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario_permissao (
  id_usuario int(11) NOT NULL,
  id_permissao int(11) NOT NULL,
  KEY user_fk_idx (id_usuario),
  KEY role_fk_idx (id_permissao),
  CONSTRAINT role_fk FOREIGN KEY (id_permissao) REFERENCES permissao(id),
  CONSTRAINT user_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS hospede (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    sobrenome VARCHAR(45) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    cep VARCHAR(10),
    logradouro VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(45),
    estado VARCHAR(45),
    pais VARCHAR(45) DEFAULT 'Brasil',
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS acomodacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    valor_diaria DECIMAL(10,2) NOT NULL,
    qtd_hospedes INT NOT NULL CHECK (qtd_hospedes > 0),
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS comodidade (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100) NOT NULL,
    tipo ENUM('MOBÍLIA', 'ELETRODOMÉSTICOS', 'UTENSÍLIOS', 'OUTROS') NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS acomodacao_comodidade (
    id_acomodacao INT NOT NULL,
    id_comodidade INT NOT NULL,
    qtd_comodidade INT NOT NULL CHECK (qtd_comodidade > 0),
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_acomodacao) REFERENCES acomodacao(id),
    FOREIGN KEY (id_comodidade) REFERENCES comodidade(id)
);

CREATE TABLE IF NOT EXISTS reserva (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_hospede BIGINT NOT NULL,
    id_acomodacao INT NOT NULL,
    data_reserva DATE NOT NULL,
    qtd_diarias INT NOT NULL CHECK (qtd_diarias > 0),
    valor_total DECIMAL(12,2) NOT NULL,
    status_reserva ENUM('PENDENTE', 'EM_ANDAMENTO', 'FINALIZADA', 'CANCELADA') NOT NULL,
    status_pagamento ENUM('PENDENTE', 'PAGO', 'CANCELADO') NOT NULL,
    data_check_in DATE,
    data_check_out DATE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_acomodacao) REFERENCES acomodacao(id),
    FOREIGN KEY (id_hospede) REFERENCES hospede(id)
);