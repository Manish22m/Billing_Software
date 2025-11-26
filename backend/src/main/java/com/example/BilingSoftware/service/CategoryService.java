package com.example.BilingSoftware.service;

import com.example.BilingSoftware.io.CategoryRequest;
import com.example.BilingSoftware.io.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse add(CategoryRequest categoryRequest);
    List<CategoryResponse> read();
    void delete(String categoryId);
}
