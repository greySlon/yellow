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
INSERT INTO user (id, login, password, enabled)
VALUES (1, 'patient', '$2a$11$9o0yeUUZW7f8xjL0Be2uYeAiLNJH1rZHGk.dn8yE/8lMXrshwdOBO', b'1');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);