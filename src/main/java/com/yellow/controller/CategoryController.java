package com.yellow.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.yellow.model.Category;
import com.yellow.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @RequestMapping(value = "/category/all", method = GET)
  public List<Category> getCategories() {
    return categoryService.getCategories();
  }
}
