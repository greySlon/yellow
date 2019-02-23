package com.yellow.domain;

import java.util.List;
import java.util.stream.Collectors;
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
  @JsonProperty(value = "img_list")
  private List<String> postPictures;
  @JsonProperty(value = "post")
  private String content;
  @JsonProperty(value = "snippet")
  private String snippet;

  public PostDtoOut() {
  }

  public PostDtoOut(Post post) {
    this.header = post.getHeader();
    this.postId = post.getId();
    this.postPictures = post.getPostImageList().stream()
        .map(p -> p.getName())
        .collect(Collectors.toList());
    this.snippet = post.getSnippet();
    this.content = post.getContent();
  }
}

