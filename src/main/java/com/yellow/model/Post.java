package com.yellow.model;


import static javax.persistence.GenerationType.IDENTITY;

import lombok.Data;

import com.yellow.photo.Picture;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post")
@Data
public class Post {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "header", nullable = false)
  private String header;
  @Column(name = "content", nullable = false, columnDefinition = "text")
  private String content;
  @Column(name = "snippet", nullable = false, length = 500)
  private String snippet;
  @OneToOne
  @JoinColumn(name = "picture_id")
  private Picture postImage;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  @Column(name = "date_time")
  private LocalDateTime time;
  @Column(name = "main_post", nullable = false, columnDefinition = "not null default b'0'")
  private Boolean mainPost;

}
