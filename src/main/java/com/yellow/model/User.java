package com.yellow.model;

import static javax.persistence.GenerationType.IDENTITY;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "login")
  private String login;
  @Column(name = "enable")
  private Boolean enable;
  @Column(name = "password")
  private String password;

}
