CREATE DATABASE IF NOT EXISTS pousada;

USE pousada;

CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(60) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    email VARCHAR(100),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS funcao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuario_funcao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    funcao_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (funcao_id) REFERENCES funcao(id)
);

CREATE TABLE IF NOT EXISTS hospede (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    sobrenome VARCHAR(45) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    cep VARCHAR(10),
    endereco VARCHAR(100),
    cidade VARCHAR(45),
    estado VARCHAR(45),
    pais VARCHAR(45) DEFAULT 'Brasil',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS acomodacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    valor_diaria DECIMAL(10,2) NOT NULL,
    quantidade_hospedes INT NOT NULL CHECK (quantidade_hospedes > 0),
    disponivel BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS acomodacao_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_acomodacao INT NOT NULL,
    id_item INT NOT NULL,
    quantidade_item INT NOT NULL CHECK (quantidade_item > 0),
    data_adicionado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_acomodacao) REFERENCES acomodacao(id),
    FOREIGN KEY (id_item) REFERENCES item(id)
);

CREATE TABLE IF NOT EXISTS reserva (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo_reserva VARCHAR(20) UNIQUE NOT NULL,
    id_hospede BIGINT NOT NULL,
    id_acomodacao INT NOT NULL,
    data_inicial DATE NOT NULL,
    qtd_diarias INT NOT NULL CHECK (qtd_diarias > 0),
    valor_diaria DECIMAL(10,2) NOT NULL,
    valor_total DECIMAL(12,2) NOT NULL,
    status_reserva ENUM('PENDENTE', 'EM_ANDAMENTO', 'FINALIZADA', 'CANCELADA') NOT NULL,
    status_pagamento ENUM('PENDENTE', 'PAGO', 'CANCELADO') NOT NULL,
    data_check_in DATE,
    data_check_out DATE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_acomodacao) REFERENCES acomodacao(id),
    FOREIGN KEY (id_hospede) REFERENCES hospede(id)
);