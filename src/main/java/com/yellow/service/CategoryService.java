package com.yellow.service;

import com.yellow.exception.AppException;
import com.yellow.model.Category;
import com.yellow.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public Category findCategory(String categoryName) {
    return categoryRepository.getByName(categoryName).orElseThrow(
        () -> new AppException("category not found")
    );
  }

  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }
}
