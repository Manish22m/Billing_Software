package com.example.BilingSoftware.service.imple;

import com.example.BilingSoftware.entity.CategoryEntity;
import com.example.BilingSoftware.io.CategoryRequest;
import com.example.BilingSoftware.io.CategoryResponse;
import com.example.BilingSoftware.repo.CategoryRepo;
import com.example.BilingSoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceimple implements CategoryService {

    @Autowired
    private final CategoryRepo categoryRepo;
    @Override
    public CategoryResponse add(CategoryRequest categoryRequest) {
        CategoryEntity newCategory= converToEntity(categoryRequest);
        newCategory=categoryRepo.save(newCategory);
        return converToResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> read() {
        return categoryRepo.findAll()
                .stream()
                .map(categoryEntity -> converToResponse(categoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String categoryId) {
        CategoryEntity existingcategory=categoryRepo.findById(categoryId).orElseThrow(()-> new RuntimeException("Category not found:"+categoryId));
        categoryRepo.delete(existingcategory);
    }


    private CategoryResponse converToResponse(CategoryEntity newCategory) {
        return CategoryResponse.builder()
                .categoryID(newCategory.getCategoryID())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgcolor(newCategory.getBgcolor())
                .imgUrl(newCategory.getImgUrl())
                .createdAt(newCategory.getCreatedAt())
                .updatedAt(newCategory.getUpdatedAt())
                .build();
    }

    private CategoryEntity converToEntity(CategoryRequest categoryRequest) {
       return CategoryEntity.builder()
                .categoryID(UUID.randomUUID().toString())
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .bgcolor(categoryRequest.getBgcolor())
                .build();
    }
}
