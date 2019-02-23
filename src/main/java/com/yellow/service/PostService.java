package com.yellow.service;


import com.yellow.domain.AppResponse;
import com.yellow.domain.PostDtoIn;
import com.yellow.domain.PostDtoOut;
import com.yellow.exception.AppException;
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

    List<PostDtoOut> collect = posts.stream()
        .map(p -> {
          PostDtoOut postDtoOut = new PostDtoOut();
          postDtoOut.setHeader(p.getHeader());
          postDtoOut.setPostId(p.getId());
          Picture postImage = p.getPostImage();
          String postPicture = postImage == null ? null : postImage.getName();
          postDtoOut.setPostPicture(postPicture);
          return postDtoOut;
        })
        .collect(Collectors.toList());

    AppResponse appResponse = new AppResponse();
    appResponse.put("posts", collect);
    appResponse.put("total_pages", total);
    return appResponse;
  }

  public void addNewPost(PostDtoIn postDtoIn) {

    Post post = new Post();

    String header = postDtoIn.getHeader();
    String content = postDtoIn.getContent();
    String snippet = postDtoIn.getSnippet();
    Category category = postDtoIn.getCategory();
    post.setHeader(header);
    post.setContent(content);
    post.setSnippet(snippet);
    post.setCategory(category);

    postRepository.save(post);
  }

  public Post findPost(Long postId) {
    return postRepository.getById(postId).orElseThrow(() -> new AppException("no post found"));
  }
}
