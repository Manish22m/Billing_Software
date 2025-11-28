package com.example.BilingSoftware.service;

import com.example.BilingSoftware.io.CategoryRequest;
import com.example.BilingSoftware.io.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    CategoryResponse add(CategoryRequest categoryRequest, MultipartFile file);
    List<CategoryResponse> read();
    void delete(String categoryID);
}
