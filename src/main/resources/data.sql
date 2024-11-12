INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, qtd_hospedes)
VALUES (1, "Domo", 590.00, 2);

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, qtd_hospedes)
VALUES (2, "Charrua (Bus)", 490.00, 2);

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, qtd_hospedes)
VALUES (3, "Suíte com Cozinha", 390.00, 3);

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, qtd_hospedes)
VALUES (4, "Chalé Família", 590.00, 5);

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, qtd_hospedes)
VALUES (5, "Cabana", 490.00, 3);

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria)
VALUES (6, "Estacionamento para Overlands", 100.00);

INSERT IGNORE INTO permissao (nome)
VALUES ('USUARIO');

INSERT IGNORE INTO permissao (nome)
VALUES ('GERENTE');

INSERT IGNORE INTO permissao (nome)
VALUES ('ADMIN');

INSERT IGNORE INTO usuario (usuario, senha, ativo) VALUES ('patrick', '12345', 1);
INSERT IGNORE INTO usuario (usuario, senha, ativo) VALUES ('alex', '23456', 1);
INSERT IGNORE INTO usuario (usuario, senha, ativo) VALUES ('john', '34567', 1);

INSERT IGNORE INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 1);
INSERT IGNORE INTO usuario_permissao (id_usuario, id_permissao) VALUES (2, 2);
INSERT IGNORE INTO usuario_permissao (id_usuario, id_permissao) VALUES (3, 3);

