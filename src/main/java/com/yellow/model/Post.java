package com.yellow.model;


import static javax.persistence.GenerationType.IDENTITY;

import com.yellow.photo.Picture;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
  @OneToMany
  @JoinTable(
      name = "post_picture",
      joinColumns = @JoinColumn(name = "post_id"),
      inverseJoinColumns = @JoinColumn(name = "picture_id")
  )
  private List<Picture> postImageList;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  @Column(name = "created")
  private LocalDateTime time;
  @Column(name = "main_post", nullable = false)
  private Boolean mainPost;

}
