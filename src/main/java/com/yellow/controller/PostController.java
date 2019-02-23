package com.yellow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.yellow.domain.AppResponse;
import com.yellow.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  @Autowired
  private PostService postService;

  @RequestMapping(value = "/post/{category}/{page}", method = GET)
  public AppResponse getPosts(
      @PathVariable("page") String categoryName,
      @PathVariable("page") Integer page) {
    return postService.getPosts(categoryName, page);
  }
}
