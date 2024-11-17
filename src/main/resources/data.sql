-- Tabela Acomodacao
INSERT IGNORE INTO acomodacao (nome, descricao, valor_diaria, qtd_hospedes)
VALUES
("Domo", "Acomodação para 2 pessoas com cama e banheiro privativo.", 590.00, 2);

INSERT IGNORE INTO acomodacao (nome, descricao, valor_diaria, qtd_hospedes)
VALUES
("Charrua (Bus)", "Veículo adaptado para acomodação de 2 pessoas.", 490.00, 2);

INSERT IGNORE INTO acomodacao (nome, descricao, valor_diaria, qtd_hospedes)
VALUES
("Suíte com Cozinha", "Suíte com cozinha equipada para 3 pessoas.", 390.00, 3);

INSERT IGNORE INTO acomodacao (nome, descricao, valor_diaria, qtd_hospedes)
VALUES
("Chalé Família", "Chalé para até 5 pessoas, com espaço para famílias.", 590.00, 5);

INSERT IGNORE INTO acomodacao (nome, descricao, valor_diaria, qtd_hospedes)
VALUES
("Cabana", "Cabana para 3 pessoas, com ambiente rústico.", 490.00, 3);

INSERT IGNORE INTO acomodacao (nome, descricao, valor_diaria, qtd_hospedes)
VALUES
("Estacionamento para Overlands", "Área de estacionamento para veículos de grande porte.", 100.00, 0);

-- Tabela Comodidade
INSERT IGNORE INTO comodidade (descricao, tipo)
VALUES
("Teste", "MOBILIA");

INSERT IGNORE INTO comodidade (descricao, tipo)
VALUES
("Teste2", "OUTROS");

-- Tabela Hospede
INSERT IGNORE INTO hospede (nome, sobrenome, cpf, data_nascimento, email, telefone, cep, logradouro, numero, complemento, bairro, cidade, estado, pais)
VALUES
("Maria", "Pena", "11111111119", "1996-11-05", "maria.pena@gmail.com", "47123456789", "12345-678", "Rua Exemplo", "123", "Apto 301", "Centro", "São Paulo", "SP", "Brasil");

INSERT IGNORE INTO hospede (nome, sobrenome, cpf, data_nascimento, email, telefone, cep, logradouro, numero, complemento, bairro, cidade, estado, pais)
VALUES
("Alice", "Pena", "22222222229", "1996-11-12", "alice.pena@gmail.com", "48987654321", "23456-789", "Avenida Central", "456", "Bloco 3", "Jardim das Flores", "Rio de Janeiro", "RJ", "Brasil");

-- Tabela Usuario
INSERT IGNORE INTO usuario (usuario, senha, ativo)
VALUES ('patrick', '12345', 1);

INSERT IGNORE INTO usuario (usuario, senha, ativo)
VALUES ('alex', '23456', 1);

INSERT IGNORE INTO usuario (usuario, senha, ativo)
VALUES ('john', '34567', 1);
