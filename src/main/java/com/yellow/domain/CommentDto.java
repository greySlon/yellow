package com.yellow.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

  private LocalDateTime created;
  private String fullName;
  private String text;
  private String userPhoto;
}
