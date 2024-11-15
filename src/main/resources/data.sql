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

INSERT IGNORE INTO usuario (usuario, senha, ativo) VALUES ('patrick', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1);
INSERT IGNORE INTO usuario (usuario, senha, ativo) VALUES ('alex', '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 1);
INSERT IGNORE INTO usuario (usuario, senha, ativo) VALUES ('john', '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', 1);

INSERT IGNORE INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 1);
INSERT IGNORE INTO usuario_permissao (id_usuario, id_permissao) VALUES (2, 2);
INSERT IGNORE INTO usuario_permissao (id_usuario, id_permissao) VALUES (3, 3);
