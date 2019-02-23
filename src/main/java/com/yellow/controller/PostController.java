package com.yellow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.yellow.domain.AppResponse;
import com.yellow.domain.PostDtoIn;
import com.yellow.domain.PostDtoOut;
import com.yellow.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  @Autowired
  private PostService postService;

  @RequestMapping(value = "/post/{category}/{page}", method = GET)
  public AppResponse getPosts(
      @PathVariable("category") String categoryName,
      @PathVariable("page") Integer page) {
    return postService.getPosts(categoryName, page);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void create(@RequestBody PostDtoIn postDtoIn) {
    postService.addNewPost(postDtoIn);


  }

}
