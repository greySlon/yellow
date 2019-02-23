CREATE TABLE user (
  id       BIGINT                        AUTO_INCREMENT PRIMARY KEY,
  login    VARCHAR(255) UNIQUE  NOT NULL,
  password VARCHAR(300)         NOT NULL,
  enabled  BIT                  NOT NULL DEFAULT b'0'
);

CREATE TABLE role (
  id   BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_role (
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT user_role UNIQUE (user_id, role_id)
);

INSERT INTO role (id, name) VALUES (1, 'USER_ROLE');
INSERT INTO role (id, name) VALUES (2, 'ADMIN_ROLE');
INSERT INTO user (id, login, password, enabled)
VALUES (1, 'Admin', '$2a$11$CVYsXiVsJIhFDDTOm20Vk.AnTbyB.rbZyYxdYQ2gs1dBC3h9Zkevy', b'1');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

CREATE TABLE category
(
  id   BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE picture
(
  id       BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  attr_alt VARCHAR(255) NOT NULL,
  name     VARCHAR(255) NOT NULL,
  CONSTRAINT UK_uniqe
  UNIQUE (name)
);

CREATE TABLE post
(
  id          BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  created     DATETIME     NULL,
  content     TEXT         NOT NULL,
  header      VARCHAR(255) NOT NULL,
  main_post   BIT          NOT NULL,
  snippet     VARCHAR(500) NOT NULL,
  date_time   DATETIME     NULL,
  post_id     BIGINT       NULL,
  user_id     BIGINT       NULL,
  category_id BIGINT       NULL,
  picture_id  BIGINT       NULL
);

CREATE INDEX FK72mt33dhhs48hf9gcqrq4fxte
  ON post (user_id);

CREATE INDEX FKcujdyjmpscm8hpiv70fbafgdb
  ON post (post_id);

CREATE INDEX FKlw8ljyti8buqh3bu8poougxto
  ON post (picture_id);

CREATE INDEX FKg6l1ydp1pwkmyj166teiuov1b
  ON post (category_id);

CREATE TABLE comment
(
  id      BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  created DATETIME NULL,
  text    TEXT     NOT NULL,
  post_id BIGINT   NOT NULL,
  user_id BIGINT   NOT NULL
);

CREATE INDEX FK8kcum44fvpupyw6f5baccx25c
  ON comment (user_id);

CREATE INDEX FKs1slvnkuemjsq2kj4h3vhx7i1
  ON comment (post_id);

INSERT INTO category (id, name) VALUES (1, 'Category-1');
INSERT INTO category (id, name) VALUES (2, 'Category-2');
INSERT INTO category (id, name) VALUES (3, 'Category-3');
INSERT INTO category (id, name) VALUES (4, 'Category-4');
INSERT INTO category (id, name) VALUES (5, 'Category-5');
INSERT INTO category (id, name) VALUES (6, 'Category-6');
INSERT INTO category (id, name) VALUES (7, 'Category-7');

INSERT INTO picture (id, attr_alt, name) VALUES (1, 'img 1','image-1.jpg');
INSERT INTO picture (id, attr_alt, name) VALUES (2, 'img 1','image-2.jpg');
INSERT INTO picture (id, attr_alt, name) VALUES (3, 'img 1','image-3.jpg');