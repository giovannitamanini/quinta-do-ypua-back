INSERT IGNORE INTO informacao_pousada (nome, endereco_1, endereco_2, cep, telefone, email, web_site, facebook, instagram, whatsapp)
VALUES ("Pousada Quinta do Ypuã", "Estrada Ipua, nº 6", "Laguna - SC", "88790-000", "(48) 99940-9732", "pousadaquintadoypua@gmail.com", "https://www.quintadoypua.com", "https://www.facebook.com/pousadaquintadoypua", "https://www.instagram.com/pousadaquintadoypua/", "+55 48 99940-9732");

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, quantidade_hospedes, descricao_camas)
VALUES (1,"Suíte com Cozinha", 300.00, 3, "1 Solteiro, 1 Casal");

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, quantidade_hospedes, descricao_camas)
VALUES (2, "Chalé Família", 500.00, 5, "1 Solteiro, 2 Casal");

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, quantidade_hospedes, descricao_camas)
VALUES (3, "Cabana", 400.00, 3, "1 Solteiro, 1 Casal");

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, quantidade_hospedes, descricao_camas)
VALUES (4, "Bus", 425.00, 2, "1 Casal");

INSERT IGNORE INTO acomodacao (id, nome, valor_diaria, quantidade_hospedes)
VALUES (5, "Estacionamento Para Overlands", 100.00, 4);
