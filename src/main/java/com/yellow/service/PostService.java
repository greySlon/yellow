package com.yellow.service;

import com.yellow.domain.AppResponse;
import com.yellow.domain.PostDto;
import com.yellow.model.Category;
import com.yellow.model.Post;
import com.yellow.photo.Picture;
import com.yellow.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private CommentService commentService;

  public AppResponse getPosts(String categoryName, Integer pageNumber) {
    Category category = categoryService.findCategory(categoryName);
    Pageable pageable = new PageRequest(pageNumber, 10);
    Page<Post> page = postRepository.getPosts(category, pageable);
    List<Post> posts = page.getContent();
    Integer total = page.getTotalPages();

    List<PostDto> collect = posts.stream()
        .map(p -> {
          PostDto postDto = new PostDto();
          postDto.setHeader(p.getHeader());
          postDto.setPostId(p.getId());
          Picture postImage = p.getPostImage();
          String postPicture = postImage == null ? null : postImage.getName();
          postDto.setPostPicture(postPicture);
          return postDto;
        })
        .collect(Collectors.toList());

    AppResponse appResponse = new AppResponse();
    appResponse.put("posts", collect);
    appResponse.put("total_pages", total);
    return appResponse;
  }
}
