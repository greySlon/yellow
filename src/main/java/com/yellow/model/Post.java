package com.yellow.model;


import static javax.persistence.GenerationType.IDENTITY;

import com.yellow.photo.Picture;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

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

}
