INSERT INTO users values(1, "2021-08-31 13:59:35.453600","carlos@email.com","Carlos Eduardo Marangoni Mendes","$2a$10$Z3.Vg4ERBJQIIVoHolb34OM90CFfKKBTcLrP6pTcuEEA/Fo6vr0Ha","carlos_marangoni");
INSERT INTO users values(2, "2021-08-30 13:59:35.453600","darlan@email.com","Darlan Ricardo Marangoni da Silva","$2a$10$Z3.Vg4ERBJQIIVoHolb34OM90CFfKKBTcLrP6pTcuEEA/Fo6vr0Ha","darlan_silva");
INSERT INTO users values(3, "2021-08-29 13:59:35.453600","maria@email.com","Maria Silva","$2a$10$Z3.Vg4ERBJQIIVoHolb34OM90CFfKKBTcLrP6pTcuEEA/Fo6vr0Ha","maria_silva");
INSERT INTO authorities values(1,"USER");
INSERT INTO authorities values(2,"ADMIN");
INSERT INTO users_authorities values(1,1);
INSERT INTO post values(1,4,1,"2021-08-31 13:59:35.453600","Postagem de teste",1,"teste.jpg",25.4,1);
INSERT INTO sale_item values(1,"Molde sutiã P",1);
INSERT INTO sale_item values(2,"Molde sutiã M",1);
INSERT INTO post_likes values(1,1);
INSERT INTO post values(2,1,0,"2021-09-01 13:59:35.453600","Segunda postagem teste",0,"teste2.jpg",30.00,1);
INSERT INTO sale_item values(1,"Molde camiseta P",2);
INSERT INTO post_comments values(1,"comentario",4,1,1);
INSERT INTO post values(3,2,2,"2021-09-05 13:59:35.453600","terceira postagem teste",1,"teste3.jpg",30.00,2);
INSERT INTO post_comments(description,stars,post_id,user_id) values("Comentario teste",4,3,3)
INSERT INTO post_likes values(3,3)
INSERT INTO sale_item values(1,"Molde camiseta P",3);
INSERT INTO users_followers values (1,3);
INSERT INTO users_followers values (2,3);