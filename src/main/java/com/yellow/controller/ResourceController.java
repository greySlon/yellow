package com.yellow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.yellow.exception.ResourceNotFoundException;

import org.springframework.http.CacheControl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ResourceController {

  @RequestMapping(value = {"/img/**", "/css/**", "/js/**", "/audio/**", "/gif/**"},method = GET)
  public byte[] getImg(HttpServletRequest request, HttpServletResponse response) {
    try {
      String filename = request.getRequestURI();
      String currentFolder = System.getProperty("user.dir");
      Path path = Paths.get(currentFolder, "build", filename);

      String headerValue = CacheControl
          .maxAge(1, TimeUnit.DAYS)
          .cachePublic()
          .getHeaderValue();
      response.addHeader("Cache-Control", headerValue);

      return Files.readAllBytes(path);
    } catch (NoSuchFileException e) {
      throw new ResourceNotFoundException(e.getMessage());
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
