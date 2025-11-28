package com.example.BilingSoftware.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadFile(MultipartFile file);
    boolean deletefile(String imgUrl);
}
