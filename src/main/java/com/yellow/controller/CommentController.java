package com.yellow.controller;

import com.yellow.domain.AppResponse;
import com.yellow.domain.CommentDto;
import com.yellow.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

  @Autowired
  private CommentService commentService;

  @RequestMapping(value = "/comment/get/{post_id}/{page}")
  public AppResponse getComments(
      @PathVariable("post_id") Long postId,
      @PathVariable("page") Optional<Integer> pageOpt) {
    Integer page = pageOpt.isPresent() ? pageOpt.get() : 0;
    return commentService.getComments(postId, page);
  }
}
