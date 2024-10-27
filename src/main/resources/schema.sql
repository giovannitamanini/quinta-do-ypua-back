CREATE DATABASE IF NOT EXISTS pousada;

USE pousada;

CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(60) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    email VARCHAR(100),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS funcao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuario_funcao (
    usuario_id INT NOT NULL,
    funcao_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (funcao_id) REFERENCES funcao(id),
    PRIMARY KEY (usuario_id, funcao_id)
);

CREATE TABLE IF NOT EXISTS hospede (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    sobrenome VARCHAR(45) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(45) NOT NULL,
    cep VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS acomodacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    valor_diaria DECIMAL(6,2) NOT NULL,
    quantidade_hospedes INT NOT NULL
);

CREATE TABLE IF NOT EXISTS item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS acomodacao_item (
    id_acomodacao INT NOT NULL,
    id_item INT NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (id_acomodacao, id_item),
    FOREIGN KEY (id_acomodacao) REFERENCES acomodacao(id),
    FOREIGN KEY (id_item) REFERENCES item(id)
);

CREATE TABLE IF NOT EXISTS reserva (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_hospede BIGINT NOT NULL,
    id_acomodacao INT NOT NULL,
    data_inicial DATE NOT NULL,
    qtd_diarias INT NOT NULL,
    status_reserva VARCHAR(20) NOT NULL,
    status_pagamento VARCHAR(30) NOT NULL,
    data_check_in DATE NOT NULL,
    data_check_out DATE NOT NULL,
    valor_total DECIMAL(6,2) NOT NULL,
    FOREIGN KEY (id_acomodacao) REFERENCES acomodacao(id),
    FOREIGN KEY (id_hospede) REFERENCES hospede(id)
);
