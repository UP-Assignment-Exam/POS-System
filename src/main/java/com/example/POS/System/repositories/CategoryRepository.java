package com.example.POS.System.repositories;

import com.example.POS.System.enums.CategoryType;
import com.example.POS.System.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Page<Category> findAllByOrderByNameDesc(Pageable pageable);

    List<Category> findAllByType(CategoryType type);
}

