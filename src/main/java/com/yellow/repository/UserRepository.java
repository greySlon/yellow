package com.yellow.repository;

import com.yellow.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> getAllById(Long userId);

  Optional<User> getAllByLogin(String login);
}
