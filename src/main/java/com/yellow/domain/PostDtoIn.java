package com.yellow.domain;

import java.util.List;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class PostDtoIn {

  @JsonProperty(value = "post_id")
  private Long postId;
  @JsonProperty(value = "title")
  private String header;
  @JsonProperty(value = "img_ids")
  private List<Long> pictureIds;
  @JsonProperty(value = "post")
  private String content;
  @JsonProperty(value = "category")
  private String category;
  @JsonProperty(value = "snippet")
  private String snippet;
  @JsonProperty(value = "main_post")
  private Boolean main;


}
