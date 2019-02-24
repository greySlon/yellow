package com.yellow.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yellow.model.Comment;
import com.yellow.model.User;
import com.yellow.photo.Picture;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDtoOut {

  @JsonProperty(value = "created")
  private LocalDateTime created;
  @JsonProperty(value = "full_name")
  private String fullName;
  @JsonProperty(value = "text")
  private String text;
  @JsonProperty(value = "user_photo")
  private String userPhoto;
  @JsonProperty(value = "comments")
  private List<CommentDtoOut> commentDtoOutList;

  public CommentDtoOut() {
  }

  public CommentDtoOut(Comment comment) {
    Picture userPhoto = comment.getUser().getUserPhoto();
    User user = comment.getUser();
    String userPhotoName = userPhoto == null ? null : userPhoto.getName();
    String fullName = user.getFirstName() + " " + user.getLastName();
    this.created = comment.getCreated();
    this.text = comment.getText();
    this.userPhoto = userPhotoName;
    this.fullName = fullName;
  }
}
