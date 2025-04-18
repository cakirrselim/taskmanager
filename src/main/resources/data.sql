-- Kullanıcıları ekleyelim
INSERT INTO users (username) VALUES ('Alice');
INSERT INTO users (username) VALUES ('Bob');

-- Roller ekleyelim (UUID kullanarak)
INSERT INTO role (id, name) VALUES ('11111111-1111-1111-1111-111111111111', 'ADMIN');
INSERT INTO role (id, name) VALUES ('22222222-2222-2222-2222-222222222222', 'USER');

-- Kullanıcılara roller atayalım
INSERT INTO users_role (user_id, role_id) VALUES (1, '11111111-1111-1111-1111-111111111111'); -- Alice -> ADMIN
INSERT INTO users_role (user_id, role_id) VALUES (2, '22222222-2222-2222-2222-222222222222'); -- Bob -> USER
