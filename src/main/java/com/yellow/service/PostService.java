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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
  @Value("${app.post-per-page}")
  private Integer postPerPage;

  public AppResponse getPosts(String categoryName, Integer pageNumber) {
    Category category = categoryService.findCategory(categoryName);
    Pageable pageable = new PageRequest(pageNumber, postPerPage);
    Page<Post> page = postRepository.getPosts(category, pageable);
    List<Post> posts = page.getContent();
    Integer total = page.getTotalPages();

    List<PostDtoOut> collect = posts.stream()
        .map(p -> new PostDtoOut(p))
        .collect(Collectors.toList());

    AppResponse appResponse = new AppResponse();
    appResponse.put("posts", collect);
    appResponse.put("total_pages", total);
    return appResponse;
  }


  public AppResponse getPostsNonCategory(Integer pageNumber) {
    Pageable pageable = new PageRequest(pageNumber, postPerPage);
    Page<Post> page = postRepository.getPostsNonCategorized(pageable);
    List<Post> posts = page.getContent();
    List<PostDtoOut> collect = posts.stream()
        .map(p -> new PostDtoOut(p))
        .collect(Collectors.toList());
    return null;
  }

  public PostDtoOut getMainPost() {
    Post post = postRepository.getMainPost()
        .orElseThrow(() -> new AppException("Main Post doesn't exist!!!"));
    PostDtoOut out = new PostDtoOut();
    out.setHeader(post.getHeader());
    out.setPostId(post.getId());
    out.setContent(post.getContent());
    Picture postImage = post.getPostImage();
    String imageName = postImage == null ? null : postImage.getName();
    out.setPostPicture(imageName);
    return out;
  }


  public void addNewPost(PostDtoIn postDtoIn) {

    Post post = new Post();

    String header = postDtoIn.getHeader();
    String content = postDtoIn.getContent();
    String snippet = postDtoIn.getSnippet();
    String categoryName = postDtoIn.getCategory();
    Boolean main = postDtoIn.getMain();
    Category category = categoryService.findCategory(categoryName);

    post.setHeader(header);
    post.setContent(content);
    post.setSnippet(snippet);
    post.setCategory(category);
    post.setMainPost(main);
    post.setTime(LocalDateTime.now());

    postRepository.save(post);
  }

  public Post findPost(Long postId) {
    return postRepository.getById(postId).orElseThrow(() -> new AppException("no post found"));
  }

}
