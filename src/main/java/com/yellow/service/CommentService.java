package com.yellow.service;

import com.yellow.domain.AppResponse;
import com.yellow.domain.CommentDto;
import com.yellow.model.Comment;
import com.yellow.model.Post;
import com.yellow.model.User;
import com.yellow.photo.Picture;
import com.yellow.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private PostService postService;
  @Value("${app.comment.count}")
  Integer commentPerPage;

  public AppResponse getComments(Long postId, Integer commentPage) {
    Pageable pageable = new PageRequest(commentPage, commentPerPage);
    Post post = postService.findPost(postId);
    Page<Comment> page = commentRepository.getAll(post, pageable);
    List<Comment> comments = page.getContent();
    List<CommentDto> collect = comments.stream()
        .map(c -> {
          CommentDto commentDto = new CommentDto();
          commentDto.setCreated(c.getCreated());
          commentDto.setText(c.getText());
          User user = c.getUser();
          Picture userPhoto = c.getUser().getUserPhoto();
          String userPhotoName = user == null ? null : userPhoto.getName();
          commentDto.setUserPhoto(userPhotoName);
          String fullName = user.getFirstName() + " " + user.getLastName();
          commentDto.setFullName(fullName);
          return commentDto;
        })
        .collect(Collectors.toList());
    AppResponse appResponse = new AppResponse();
    appResponse.put("comments", collect);
    return appResponse;
  }
}
