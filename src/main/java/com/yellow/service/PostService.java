package com.yellow.service;


import com.yellow.domain.AppResponse;
import com.yellow.domain.PostDtoIn;
import com.yellow.domain.PostDtoOut;
import com.yellow.domain.PostHeader;
import com.yellow.exception.AppException;
import com.yellow.model.Category;
import com.yellow.model.Post;
import com.yellow.photo.Picture;
import com.yellow.photo.PictureRepositoryM;
import com.yellow.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

  @Autowired
  private PictureRepositoryM pictureRepository;
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
    Integer total = page.getTotalPages();
    List<PostDtoOut> collect = posts.stream()
        .map(p -> new PostDtoOut(p))
        .collect(Collectors.toList());
    AppResponse appResponse = new AppResponse();
    appResponse.put("posts", collect);
    appResponse.put("total_pages", total);
    return appResponse;
  }

  public PostDtoOut getMainPost() {
    Optional<Post> mainPost = postRepository.getMainPost();
    if (mainPost.isPresent()) {
      Post post = mainPost.get();
      return new PostDtoOut(post);
    } else {
      Post lastPost = postRepository.getLastPost(new PageRequest(0, 1)).get(0);
      return new PostDtoOut(lastPost);
    }
  }


  public void addOrUpdatePost(PostDtoIn postDtoIn) {
    Long postId = postDtoIn.getPostId();
    Post post = (postId != null)
        ? postRepository.getById(postId).orElseThrow(() -> new AppException("post not found"))
        : new Post();

    String header = postDtoIn.getHeader();
    String content = postDtoIn.getContent();
    String snippet = postDtoIn.getSnippet();
    String categoryName = postDtoIn.getCategory();
    Boolean main = postDtoIn.getMain();
    if (main) {
      uncheckMain();
    }
    List<Long> ids = postDtoIn.getPictureIds();
    Category category = categoryService.findCategory(categoryName);

    List<Picture> pictures = pictureRepository.findAllById(ids);
    post.setPostImageList(pictures);
    if (header != null) {
      post.setHeader(header);
    }
    if (content != null) {
      post.setContent(content);
    }
    if (snippet != null) {
      post.setSnippet(snippet);
    }
    post.setCategory(category);
    post.setMainPost(main);
    post.setTime(LocalDateTime.now());
    postRepository.save(post);
  }

  private void uncheckMain() {
    Optional<Post> mainPost = postRepository.getMainPost();
    if (mainPost.isPresent()) {
      Post p = mainPost.get();
      p.setMainPost(false);
      postRepository.save(p);
    }
  }

  public Post findPost(Long postId) {
    return postRepository.getById(postId).orElseThrow(() -> new AppException("no post found"));
  }

  public PostDtoOut getOnePost(Long postId) {
    Post post = this.findPost(postId);
    return new PostDtoOut(post);
  }

  public List<PostHeader> getHeaders(String match, Pageable pageable) {
    Page<Post> postPage = postRepository.findByHeaderContaining(match, pageable);
    return postPage.getContent().stream()
        .map(p -> new PostHeader(p))
        .collect(Collectors.toList());
  }

}
