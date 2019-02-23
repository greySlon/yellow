package com.yellow.photo;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

@Component
public class PictureServiceFS {

  private Map<String, Dimension> folderDimensionMap = new HashMap<>();
  @Value("#{'${app.picture.folders-resized-dimensions}'.split(';')}")
  private List<String> folderProperty;
  @Value("${app.picture.quality}")
  private float quality;
  @Value("${app.picture.folder.origin}")
  private String originFolder;
  @Autowired
  private StructureCreator structureCreator;

  @PostConstruct
  public void init() {
    for (String f : folderProperty) {
      String[] split = f.split(",");
      int width = Integer.valueOf(split[1]).intValue();
      int height = Integer.valueOf(split[2]).intValue();
      String folder = split[0];
      Dimension dimension = new Dimension(width, height, 9999);
      folderDimensionMap.put(folder, dimension);
    }
  }

  public String savePicture(InputStream inputStream, String originalName)
      throws IOException {
    String fileName = String.format("%s-%s", Instant.now().getEpochSecond(), originalName);
    Path origin = saveOrigin(inputStream, fileName);

    for (Entry<String, Dimension> item : folderDimensionMap.entrySet()) {
      String folder = item.getKey();
      Dimension dimension = item.getValue();
      Path destination = structureCreator.getAbsolutePath(folder);
      resize(origin.toString(), destination.toString(), dimension);
    }
    return fileName;
  }

  private Path saveOrigin(InputStream inputStream, String fileName) throws IOException {
    Path origin = structureCreator.getAbsolutePath(originFolder).resolve(fileName);
    Files.copy(inputStream, origin);
    return origin;
  }

  private void resize(String source, String destination, Dimension dimension)
      throws IOException {
    int width = dimension.getWidth();
    int height = dimension.getHeight();

    Thumbnails.of(source)
        .size(width, height)
        .outputQuality(quality)
//          .allowOverwrite(true)
        .toFiles(new File(destination), Rename.NO_CHANGE);
  }


  static class Dimension {

    private int width;
    private int height;
    private int mock;

    public Dimension(int width, int height, int mock) {
      this.width = width;
      this.height = height;
      this.mock = mock;
    }

    public int getWidth() {
      return width < 0 ? mock : width;
    }

    public int getHeight() {
      return height < 0 ? mock : width;
    }
  }
}
