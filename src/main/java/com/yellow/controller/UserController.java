package com.yellow.controller;

import com.yellow.domain.UserDto;
import com.yellow.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration")
public class UserController {

  @Autowired
  private UserService userService;


  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void create(@RequestBody UserDto userDto) {
    userService.addNewUser(userDto);
  }
}

