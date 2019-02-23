package com.yellow.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class PictureServiceImpl implements PictureService {

  @Autowired
  private PictureRepositoryM pictureRepositoryM;
  @Autowired
  private PictureServiceFS pictureServiceFS;

  @Override
  @Transactional
  public void addPictures(List<MultipartFile> files, String description)
      throws IOException {
    for (MultipartFile file : files) {
      InputStream inputStream = file.getInputStream();
      String originalFilename = file.getOriginalFilename();

      String fileName = pictureServiceFS.savePicture(inputStream, originalFilename);
      Picture picture = new Picture(fileName, description);
      pictureRepositoryM.save(picture);
    }
  }

  @Override
  public Page<Picture> findByPartialAlt(String part, Pageable pageable) {
    return pictureRepositoryM.findByAltContaining(part, pageable);
  }

  @Override
  public Picture findById(Long pictureId) {
    return pictureRepositoryM.findById(pictureId)
        .orElseThrow(() -> new RuntimeException("PictureNotFoundException"));
  }
}
