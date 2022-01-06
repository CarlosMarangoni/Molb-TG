INSERT INTO users values(1, '2021-08-31 13:59:35.453600','Costureiro com vasta experiência em confecção de máscaras.','carlos@email.com','Carlos Eduardo Marangoni Mendes','$2a$10$Z3.Vg4ERBJQIIVoHolb34OM90CFfKKBTcLrP6pTcuEEA/Fo6vr0Ha','https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/carlos.jpg','carlos_marangoni');
INSERT INTO users values(2, '2021-08-30 13:59:35.453600','Revendedor de modelagens de vestido','darlan@email.com','Darlan Ricardo Marangoni da Silva','$2a$10$Z3.Vg4ERBJQIIVoHolb34OM90CFfKKBTcLrP6pTcuEEA/Fo6vr0Ha','https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/darlan.jpg','darlan_silva');
INSERT INTO users values(3, '2021-08-29 13:59:35.453600','Costureira com canal no youtube com mais de 1 milhão de inscritos.','maria@email.com','Maria Silva','$2a$10$Z3.Vg4ERBJQIIVoHolb34OM90CFfKKBTcLrP6pTcuEEA/Fo6vr0Ha','https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/mari_costura.jpg','mari_costura');
INSERT INTO roles values(1,'ROLE_USER');
INSERT INTO roles values(2,'ROLE_ADMIN');
INSERT INTO users_roles values(1,1);
INSERT INTO post values(1,4,1,'2021-08-31 13:59:35.453600','Postagem de teste',1,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/calca1.png','Calça simples',1);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,1);
INSERT INTO sale_item values(2,'Molde sutiã M',30,1);
INSERT INTO post_likes values(1,1);
INSERT INTO post values(2,1,0,'2021-09-01 13:59:35.453600','Segunda postagem teste',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/calca2.jpg','Calça de couro',1);
INSERT INTO sale_item values(1,'Molde camiseta P',20,2);
INSERT INTO post_comments values(1,'comentario',4,1,1);
INSERT INTO post values(3,2,2,'2021-09-05 13:59:35.453600','terceira postagem teste',1,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/calca3.jpg','Calça jeans',2);
INSERT INTO post_comments(description,stars,post_id,user_id) values('Comentario teste',4,3,3);
INSERT INTO post_likes values(3,3);
INSERT INTO sale_item values(1,'Molde mascara P',0,3);
INSERT INTO post values(4,3,0,'2021-08-31 13:59:35.453600','Quarta postagem de teste',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/camiseta1.jpg','Camiseta simples',1);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,4);
INSERT INTO sale_item values(2,'Molde sutiã M',30,4);
INSERT INTO post values(5,4,0,'2021-08-31 13:59:35.453600','Postagem de teste 5',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/camiseta2.jpg','Regata simples',2);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,5);
INSERT INTO sale_item values(2,'Molde sutiã M',30,5);
INSERT INTO post values(6,4,0,'2021-08-31 13:59:35.453600','Postagem de teste 6',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/camiseta3.jpg','Regata long',3);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,6);
INSERT INTO sale_item values(2,'Molde sutiã M',30,6);
INSERT INTO post values(7,4,0,'2021-08-31 13:59:35.453600','Postagem de teste 7',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/lingerie1.jpg','Lingerie sem elastico',2);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,7);
INSERT INTO sale_item values(2,'Molde sutiã M',30,7);
INSERT INTO post values(8,4,0,'2021-08-31 13:59:35.453600','Postagem de teste 8',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/lingerie2.jpg','Conjunto simples',1);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,8);
INSERT INTO sale_item values(2,'Molde sutiã M',30,8);
INSERT INTO post values(9,4,0,'2021-08-31 13:59:35.453600','Postagem de teste 9',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/mascara1.jpg','Máscara microfribra',3);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,9);
INSERT INTO sale_item values(2,'Molde sutiã M',30,9);
INSERT INTO post values(10,4,0,'2021-08-31 13:59:35.453600','Postagem de teste 10',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/mascara2.jpg','Máscara simples',2);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,10);
INSERT INTO sale_item values(2,'Molde sutiã M',30,10);
INSERT INTO post values(11,4,0,'2021-08-31 13:59:35.453600','Postagem de teste 11',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/mascara3.jpeg','Máscara fácil',3);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,11);
INSERT INTO sale_item values(2,'Molde sutiã M',30,11);
INSERT INTO post values(12,4,0,'2021-08-31 13:59:35.453600','Postagem de teste 12',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/vestido1.jpg','Vestido simples',1);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,12);
INSERT INTO sale_item values(2,'Molde sutiã M',30,12);
INSERT INTO post values(13,4,0,'2021-08-31 13:59:35.453600','Postagem de teste 13',0,'https://spring-amazon-storage-molsew.s3.sa-east-1.amazonaws.com/vestido2.jpg','Vestido festivo',2);
INSERT INTO sale_item values(1,'Molde sutiã P',25.2,13);
INSERT INTO sale_item values(2,'Molde sutiã M',30,13);
INSERT INTO users_followers values (1,3);
INSERT INTO users_followers values (2,3);
INSERT INTO users_following values (3,1);
INSERT INTO users_following values (3,2);