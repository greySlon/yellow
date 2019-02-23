package com.yellow.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yellow.model.Post;
import com.yellow.photo.Picture;


@Data
public class PostDtoOut {

  @JsonProperty(value = "id")
  private Long postId;
  @JsonProperty(value = "title")
  private String header;
  @JsonProperty(value = "img")
  private String postPicture;
  @JsonProperty(value = "post")
  private String content;
  @JsonProperty(value = "snippet")
  private String snippet;

  public PostDtoOut() {
  }

  public PostDtoOut(Post post) {
    this.header = post.getHeader();
    this.postId = post.getId();
    Picture postImage = post.getPostImage();
    String postPicture = postImage == null ? null : postImage.getName();
    this.postPicture = postPicture;
    this.snippet = post.getSnippet();
    this.content = post.getContent();
  }
}

