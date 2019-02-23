package com.yellow.photo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

@Component
public class StructureCreator {

  @Value("${app.static-root}")
  private String root;
  @Value("#{'${app.static-root.folders}'.split(',')}")
  private List<String> folders;

  public StructureCreator() {
  }

  @PostConstruct
  public void init() throws IOException {
    for (String url : folders) {
      Path folderPath = Paths.get(root).toAbsolutePath().resolve(Paths.get(url));
      getAbsolutePath(folderPath);
    }
  }

  public Path getAbsolutePath(String folder) throws IOException {
    return getAbsolutePath(Paths.get(folder));
  }

  private Path getAbsolutePath(Path folderPath) throws IOException {
    if (!Files.exists(folderPath)) {
      folderPath = Files.createDirectories(folderPath);
    }
    return folderPath.toAbsolutePath();
  }
}