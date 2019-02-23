INSERT INTO role (id, name) VALUES (1, 'USER_ROLE');
INSERT INTO role (id, name) VALUES (2, 'ADMIN_ROLE');

INSERT INTO user (id, login, password, enabled)
VALUES (1, 'Admin', '$2a$11$CVYsXiVsJIhFDDTOm20Vk.AnTbyB.rbZyYxdYQ2gs1dBC3h9Zkevy', b'1');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);


INSERT INTO category (id, name) VALUES (1, 'Category-1');
INSERT INTO category (id, name) VALUES (2, 'Category-2');
INSERT INTO category (id, name) VALUES (3, 'Category-3');
INSERT INTO category (id, name) VALUES (4, 'Category-4');
INSERT INTO category (id, name) VALUES (5, 'Category-5');
INSERT INTO category (id, name) VALUES (6, 'Category-6');
INSERT INTO category (id, name) VALUES (7, 'Category-7');

INSERT INTO picture (id, attr_alt, name) VALUES (1, 'img 1', 'image-1.jpg');
INSERT INTO picture (id, attr_alt, name) VALUES (2, 'img 1', 'image-2.jpg');
INSERT INTO picture (id, attr_alt, name) VALUES (3, 'img 1', 'image-3.jpg');