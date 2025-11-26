package com.example.BilingSoftware.controller;

import com.example.BilingSoftware.io.CategoryRequest;
import com.example.BilingSoftware.io.CategoryResponse;
import com.example.BilingSoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.add(categoryRequest);
    }

    @GetMapping
    public List<CategoryResponse> fetchAllCategories(){
        return categoryService.read();
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable String categoryId){
        try {
            categoryService.delete(categoryId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"+categoryId);
        }
    }
}
