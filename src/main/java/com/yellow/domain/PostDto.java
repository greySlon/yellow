package com.yellow.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yellow.model.Comment;

import java.util.List;

@Data
public class PostDto {

  @JsonProperty(value = "id")
  private Long postId;
  @JsonProperty(value = "title")
  private String header;
  @JsonProperty(value = "img")
  private String postPicture;
  @JsonProperty(value = "comments")
  private List<Comment> comments;
}