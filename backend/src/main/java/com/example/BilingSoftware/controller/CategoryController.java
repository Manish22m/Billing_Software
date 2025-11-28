package com.example.BilingSoftware.controller;

import com.example.BilingSoftware.entity.CategoryEntity;
import com.example.BilingSoftware.io.CategoryRequest;
import com.example.BilingSoftware.io.CategoryResponse;
import com.example.BilingSoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestPart("category") String categoryString,
                                        @RequestPart("file")MultipartFile file){
        ObjectMapper objectMapper= new ObjectMapper();
        CategoryRequest categoryRequest=null;
        try{
            categoryRequest=objectMapper.readValue(categoryString, CategoryRequest.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid JSON in `category` field: " + ex.getMessage());
        }

        try {
            return categoryService.add(categoryRequest, file);
        } catch (Exception serviceEx) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while creating category: " + serviceEx.getMessage());
        }
    }

    @GetMapping
    public List<CategoryResponse> fetchAllCategories(){
        return categoryService.read();
    }

    @DeleteMapping("/{categoryID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable String categoryID){
        try {
            categoryService.delete(categoryID);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found"+categoryID);
        }
    }
}
