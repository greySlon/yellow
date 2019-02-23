package com.yellow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/*
  * controller handled thrown application exceptions
  * */
@ControllerAdvice
@RestController
public class ExceptionHandlerController {

  /*
  * create response on access denied
  * @return HttpStatus 403
  * */
  @RequestMapping(value = "/login", method = GET)
  public String forbidden(HttpServletResponse response) {
    response.setStatus(403);
    return "access denied";
  }

  @RequestMapping(value = "/secured-api/tt", method = GET)
  public String test() {
    return "access to secured-api";
  }

  @RequestMapping(value = "/login-successful", method = POST)
  public String loginSuccessful() {
    return "login successful";
  }

  /*
  * create response on successful logout
  * @return HttpStatus 200
  * */
  @RequestMapping(value = "/logout-success", method = GET)
  public String logoutsuccess() {
    return "logout successful";
  }
}