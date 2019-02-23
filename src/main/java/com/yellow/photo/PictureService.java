package com.yellow.photo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

public interface PictureService {

  @Transactional
  void addPictures(List<MultipartFile> files, String description)
      throws IOException;

  Page<Picture> findByPartialAlt(String part, Pageable pageable);

  Picture findById(Long pictureId);
}
