package com.example.assiment_springboot.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<String> saveImage(MultipartFile[] multipartFiles);

    String saveOneImage(MultipartFile multipartFile);

    Resource load(String filename);
}
