package com.yellow.repository;

import com.yellow.model.Category;
import com.yellow.model.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Optional<Post> getById(Long posId);

  @Query("select p from Post p where p.category =?1 order by  p.time")
  Page<Post> getPosts(Category category, Pageable pageable);
}
