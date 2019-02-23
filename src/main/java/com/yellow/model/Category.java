package com.yellow.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
}
