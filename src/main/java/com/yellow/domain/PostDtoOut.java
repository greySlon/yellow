package com.yellow.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


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

}

