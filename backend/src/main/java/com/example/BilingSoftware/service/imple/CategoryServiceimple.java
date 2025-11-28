package com.example.BilingSoftware.service.imple;

import com.example.BilingSoftware.entity.CategoryEntity;
import com.example.BilingSoftware.io.CategoryRequest;
import com.example.BilingSoftware.io.CategoryResponse;
import com.example.BilingSoftware.repo.CategoryRepo;
import com.example.BilingSoftware.service.CategoryService;
import com.example.BilingSoftware.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceimple implements CategoryService {

    @Autowired
    private final CategoryRepo categoryRepo;

    @Autowired
    private final FileUploadService fileUploadService;
    @Override
    public CategoryResponse add(CategoryRequest categoryRequest, MultipartFile file) {
        String imgUrl=fileUploadService.uploadFile(file);
        CategoryEntity newCategory= converToEntity(categoryRequest);
        newCategory.setImgUrl(imgUrl);
        newCategory=categoryRepo.save(newCategory);
        System.out.println("Saved id: " +newCategory.getCategoryID());
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
    public void delete(String categoryID) {
        CategoryEntity existingcategory =categoryRepo.findByCategoryID(categoryID).orElseThrow(()-> new RuntimeException("Category not found:"+categoryID));
        fileUploadService.deletefile(existingcategory.getImgUrl());
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
