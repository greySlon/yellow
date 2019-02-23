package com.yellow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.yellow.domain.AppResponse;
import com.yellow.domain.CommentDtoIn;
import com.yellow.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @RequestMapping(value = "/get/{post_id}/{page}", method = GET)
  public AppResponse getComments(
      @PathVariable("post_id") Long postId,
      @PathVariable("page") Optional<Integer> pageOpt) {
    Integer page = pageOpt.isPresent() ? pageOpt.get() : 0;
    return commentService.getComments(postId, page);
  }

  @RequestMapping(value = "/add", method = POST)
  public void addComment(@RequestBody CommentDtoIn commentDtoIn) {
    commentService.addComment(commentDtoIn);
  }

  @RequestMapping(value = "/{comment_id}", method = POST)
  public void deleteComment(@PathVariable("comment_id") Long commentId) {
    commentService.deleteComment(commentId);
  }
}
