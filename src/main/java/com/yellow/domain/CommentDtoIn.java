package com.yellow.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class CommentDtoIn {

  @JsonProperty(value = "user_id")
  private Long userId;
  @JsonProperty(value = "post_id")
  private Long postId;
  @JsonProperty(value = "text")
  private String text;
}
