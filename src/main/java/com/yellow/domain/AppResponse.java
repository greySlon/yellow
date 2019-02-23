package com.yellow.domain;

import java.util.HashMap;

public class AppResponse extends HashMap<String, Object> {

  public AppResponse put(String key, Object value) {
    super.put(key, value);
    return this;
  }
}