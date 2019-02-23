package com.yellow.photo;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "picture")
public class Picture {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "name", unique = true, nullable = false)
  private String name;
  @Column(name = "attr_alt", nullable = false)
  private String alt;

  public Picture() {
  }

  public Picture(String name, String alt) {
    this.name = name;
    this.alt = alt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAlt() {
    return alt;
  }

  public void setAlt(String alt) {
    this.alt = alt;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Picture picture = (Picture) obj;
    return Objects.equals(name, picture.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
