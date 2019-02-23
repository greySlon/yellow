package com.yellow.controller;

import com.yellow.domain.PostDtoIn;
import com.yellow.photo.Picture;
import com.yellow.photo.PictureService;

import com.yellow.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/photo")
public class AdminController {

  @Autowired
  private PictureService pictureService;
  @Autowired
  private PostService postService;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void create(@RequestBody PostDtoIn postDtoIn) {
    postService.addNewPost(postDtoIn);
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public void upload(
      @RequestParam("file") List<MultipartFile> files,
      @RequestParam(name = "alt") String description,
      Model model) throws IOException {
    try {
      if (description == null || description.replaceAll("\\s", "").isEmpty()) {
        model.addAttribute("error", "описание не указано");
      } else if (files.isEmpty()) {
        model.addAttribute("error", "файл не выбран");
      } else {
        pictureService.addPictures(files, description);
      }
    } catch (IOException e) {
      model.addAttribute("error", "ошибка записи");
    }
  }

  @RequestMapping(value = "/get/by_match", method = RequestMethod.POST)
  @ResponseBody
  public Page<Picture> getPicsByAltMatch(@RequestParam(name = "match") String match,
      Pageable pageable) {
    return pictureService.findByPartialAlt(match, pageable);
  }
}
