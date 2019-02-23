package com.yellow.model;

import static javax.persistence.GenerationType.IDENTITY;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@Data
public class Category {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @JsonProperty(value = "category_id")
  private Long id;
  @JsonProperty(value = "category_name")
  @Column(name = "name", nullable = false)
  private String name;
}
