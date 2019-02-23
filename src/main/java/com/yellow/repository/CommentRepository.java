package com.yellow.repository;

import com.yellow.model.Comment;
import com.yellow.model.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query("select c from Comment c where c.post = ?1 order by c.created desc")
  Page<Comment> getAll(Post post, Pageable pageable);
}
