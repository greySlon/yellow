package com.yellow.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@Data
public class CommentDto {

  @JsonProperty(value = "created")
  private LocalDateTime created;
  @JsonProperty(value = "full_name")
  private String fullName;
  @JsonProperty(value = "text")
  private String text;
  @JsonProperty(value = "user_photo")
  private String userPhoto;
}
