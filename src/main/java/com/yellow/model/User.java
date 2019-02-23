package com.yellow.model;

import static javax.persistence.GenerationType.IDENTITY;

import lombok.Data;

import com.yellow.photo.Picture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
  @Column(name = "first_name", nullable = false)
  private String firstName;
  @Column(name = "last_name", nullable = false)
  private String lastName;
  @Column(name = "enabled", columnDefinition = "default b'0'")
  private Boolean enabled;
  @Column(name = "password")
  private String password;
  @OneToOne
  @JoinColumn(name = "picture_id")
  private Picture userPhoto;
}
