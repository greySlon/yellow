package com.yellow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.yellow.domain.AppResponse;
import com.yellow.domain.PostDtoOut;
import com.yellow.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/post")
public class PostController {

  @Autowired
  private PostService postService;

  @RequestMapping(value = "/{category}/{page}", method = GET)
  public AppResponse getPosts(
      @PathVariable("category") String categoryName,
      @PathVariable("page") Integer page) {
    return postService.getPosts(categoryName, page);
  }

  @RequestMapping(value = "/non-category/{page}", method = GET)
  public AppResponse getPosts(@PathVariable("page") Integer page) {
    return postService.getPostsNonCategory(page);
  }

  @RequestMapping(value = "/main", method = GET)
  public PostDtoOut getMain() {
    return postService.getMainPost();
  }

  @RequestMapping(value = "/{id}", method = GET)
  public PostDtoOut GetOnePost(@PathVariable("id") Long id) {
    return postService.getOnePost(id);
  }
}
