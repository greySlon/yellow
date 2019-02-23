package com.yellow.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yellow.model.Post;

@Data
public class PostHeader {

  @JsonProperty(value = "post_id")
  private Long postId;
  @JsonProperty(value = "header")
  private String header;

  public PostHeader() {
  }

  public PostHeader(Post post) {
    this.postId = post.getId();
    this.header = post.getHeader();
  }
}
