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
public class CommentController {

  @Autowired
  private CommentService commentService;

  @RequestMapping(value = "/comment/get/{post_id}/{page}", method = GET)
  public AppResponse getComments(
      @PathVariable("post_id") Long postId,
      @PathVariable("page") Optional<Integer> pageOpt) {
    Integer page = pageOpt.isPresent() ? pageOpt.get() : 0;
    return commentService.getComments(postId, page);
  }

  @RequestMapping(value = "/comment/add", method = POST)
  public void addComment(@RequestBody CommentDtoIn commentDtoIn) {
    commentService.addComment(commentDtoIn);
  }
}
