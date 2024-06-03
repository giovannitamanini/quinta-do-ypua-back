CREATE DATABASE IF NOT EXISTS pousada;

USE pousada;

CREATE TABLE IF NOT EXISTS informacao_pousada (
	nome VARCHAR(45) PRIMARY KEY,
    endereco_1 VARCHAR(60),
    endereco_2 VARCHAR(60),
    cep VARCHAR(9),
    telefone VARCHAR(15),
    email VARCHAR(45),
    web_site VARCHAR(100),
    facebook VARCHAR(100),
    instagram VARCHAR(100),
    whatsapp VARCHAR(100)
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
	id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor_diaria DECIMAL(6,2) NOT NULL,
	quantidade_hospedes INT NOT NULL,
    descricao_camas VARCHAR(50) DEFAULT "-"
);

CREATE TABLE IF NOT EXISTS reserva (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    status_reserva VARCHAR(10) NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    numero_pernoites INT NOT NULL,
    custo DECIMAL(6,2) NOT NULL,
    status_pagamento VARCHAR(30) NOT NULL,
    id_acomodacao INT NOT NULL,
    id_hospede BIGINT NOT NULL,
    FOREIGN KEY (id_acomodacao) REFERENCES acomodacao(id),
    FOREIGN KEY (id_hospede) REFERENCES hospede(id)
);
