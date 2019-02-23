package com.yellow.repository;

import com.yellow.model.Category;
import com.yellow.model.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Optional<Post> getById(Long posId);

  @Query("select p from Post p where p.category =?1 and p.mainPost= false order by  p.time")
  Page<Post> getPosts(Category category, Pageable pageable);

  @Query("select p from Post p where p.mainPost= false order by  p.time")
  Page<Post> getPostsNonCategorized(Pageable pageable);

  @Query("select p from Post p where p.mainPost = true")
  Optional<Post> getMainPost();

  Page<Post> findByHeaderContaining(String part, Pageable pageable);


  @Query("select p from Post p order by p.time desc ")
  List<Post> getLastPost(Pageable pageable);

}
