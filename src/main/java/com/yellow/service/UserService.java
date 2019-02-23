package com.yellow.service;

import com.yellow.domain.UserDto;
import com.yellow.exception.AppException;
import com.yellow.model.User;
import com.yellow.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;


  public void addNewUser(UserDto userDto) {
    User user = new User();

    String login = userDto.getLogin();
    String firstName = userDto.getFirstName();
    String lastName = userDto.getLastame();
    String password = userDto.getPassword();

    Optional<User> allByLogin = userRepository.getAllByLogin(login);
    if (allByLogin.isPresent()) {
      throw new AppException("User already exist!!!");
    }

    user.setLogin(login);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setPassword(password);

    userRepository.save(user);
  }


}
