package com.yellow.model;


import static javax.persistence.GenerationType.IDENTITY;

import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
@Data
public class Comment {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;
  @Column(name = "text", nullable = false, columnDefinition = "text")
  private String text;
  @Column(name = "created")
  private LocalDateTime created;

}
