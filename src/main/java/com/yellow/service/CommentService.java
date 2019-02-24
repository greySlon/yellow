package com.yellow.service;

import com.yellow.domain.AppResponse;
import com.yellow.domain.CommentDtoIn;
import com.yellow.domain.CommentDtoOut;
import com.yellow.exception.AppException;
import com.yellow.model.Comment;
import com.yellow.model.Post;
import com.yellow.model.User;
import com.yellow.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

  @Autowired
  private UserService userService;
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
    List<CommentDtoOut> collect = comments.stream()
        .map(c -> new CommentDtoOut(c))
        .collect(Collectors.toList());
    AppResponse appResponse = new AppResponse();
    appResponse.put("comments", collect);
    return appResponse;
  }

  private List<Comment> getInnerComment(Comment comment, Post post, Pageable pageable) {
    Page<Comment> page = commentRepository.getAll(post, pageable);
    return page.getContent();
  }

  public void addComment(CommentDtoIn commentDtoIn) {
    Long postId = commentDtoIn.getPostId();
    Long userId = commentDtoIn.getUserId();
    String text = commentDtoIn.getText();
    Long commentedUserId = commentDtoIn.getCommentedUserId();

    Post post = postService.findPost(postId);
    User user = userService.getUser(userId);
    User userCommented = userService.getUser(commentedUserId);

    Comment comment = new Comment();
    comment.setCreated(LocalDateTime.now());
    comment.setText(text);
    comment.setUser(user);
    comment.setUserCommented(userCommented);
    comment.setPost(post);
    commentRepository.save(comment);
  }


  public void deleteComment(Long commentId) {
    Comment comment = commentRepository.getById(commentId)
        .orElseThrow(() -> new AppException("no such comment"));
    commentRepository.delete(comment);
  }
}
