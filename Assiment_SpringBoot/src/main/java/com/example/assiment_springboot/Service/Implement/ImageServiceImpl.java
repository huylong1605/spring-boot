package com.example.assiment_springboot.Service.Implement;

import com.example.assiment_springboot.Service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final Path root = Paths.get("uploads");

    @Override
    public List<String> saveImage(MultipartFile[] multipartFiles) {
        List<String> imageUrl = new ArrayList<>();
        if(root.getRoot() == null) {
            init();
        }
        for(MultipartFile file: multipartFiles){
            try {
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                imageUrl.add("http://localhost:8080/files/"+ file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imageUrl;
    }

    private void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }


    @Override
    public String saveOneImage(MultipartFile multipartFile) {
        if (root.getRoot() == null) {
            init();
        }
        try {
            Files.copy(multipartFile.getInputStream(), this.root.resolve(multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "http://localhost:8080/files/"+ multipartFile.getOriginalFilename();
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
