CREATE TABLE user (
  id       BIGINT                        AUTO_INCREMENT PRIMARY KEY,
  login    VARCHAR(255) UNIQUE  NOT NULL,
  password VARCHAR(300)         NOT NULL,
  enabled  BIT                  NOT NULL DEFAULT b'0'
);

CREATE TABLE role (
  id   BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_role (
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT user_role UNIQUE (user_id, role_id),
  CONSTRAINT fk_user
  FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT fk_role
  FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE category
(
  id   BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT uk_cat_unique UNIQUE (name)
);

CREATE TABLE picture
(
  id       BIGINT AUTO_INCREMENT PRIMARY KEY,
  attr_alt VARCHAR(255) NOT NULL,
  name     VARCHAR(255) NOT NULL,
  CONSTRAINT UK_uniqe UNIQUE (name)
);

CREATE TABLE post
(
  id          BIGINT                AUTO_INCREMENT PRIMARY KEY,
  created     DATETIME     NULL,
  content     TEXT         NOT NULL,
  header      VARCHAR(255) NOT NULL,
  main_post   BIT          NOT NULL DEFAULT b'0',
  snippet     VARCHAR(500) NOT NULL,
  category_id BIGINT       NULL,
  CONSTRAINT fk_post_cat
  FOREIGN KEY (category_id) REFERENCES category (id)
);
CREATE TABLE post_picture
(
  post_id    BIGINT NOT NULL,
  picture_id BIGINT NOT NULL,
  CONSTRAINT fk_post_pics_post
  FOREIGN KEY (post_id) REFERENCES post (id),
  CONSTRAINT fk_post_pics_picture
  FOREIGN KEY (picture_id) REFERENCES picture (id)
);


CREATE TABLE comment
(
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  created DATETIME NULL,
  text    TEXT     NOT NULL,
  post_id BIGINT   NOT NULL,
  user_id BIGINT   NOT NULL,
  CONSTRAINT fk_comment_post
  FOREIGN KEY (post_id) REFERENCES post (id),
  CONSTRAINT fk_comment_user
  FOREIGN KEY (user_id) REFERENCES user (id)
);