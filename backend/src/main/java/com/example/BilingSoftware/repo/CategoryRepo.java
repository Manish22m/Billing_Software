package com.example.BilingSoftware.repo;

import com.example.BilingSoftware.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findById(String categoryId);

}
