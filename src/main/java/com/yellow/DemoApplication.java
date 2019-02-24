package com.yellow;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class DemoApplication {

  @RequestMapping(value = {"/", "/about", "/info"}, method = GET)
  String home() {
    return "index";
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}