package com.yellow.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yellow.model.Category;
import lombok.Data;

@Data
public class PostDtoIn {
  @JsonProperty(value = "title")
  private String header;
  @JsonProperty(value = "img_id")
  private Long pictureId;
  @JsonProperty(value = "post")
  private String content;
  @JsonProperty(value = "category")
  private String category;
  @JsonProperty(value = "snippet")
  private String snippet;
  @JsonProperty(value = "main_post")
  private Boolean main;



}
