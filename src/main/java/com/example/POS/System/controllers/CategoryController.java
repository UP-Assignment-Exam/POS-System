package com.example.POS.System.controllers;

import com.example.POS.System.DTO.CategoryDTO;
import com.example.POS.System.DTO.RoleDTO;
import com.example.POS.System.enums.CategoryType;
import com.example.POS.System.services.CategoryServices;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;

    @GetMapping()
    public ResponseEntity<GlobalResponse<List<CategoryDTO>>> getAll() {
        return categoryServices.getList();
    }

    @GetMapping("/type")
    public ResponseEntity<GlobalResponse<List<CategoryDTO>>> getAllByType(@RequestParam CategoryType type) {
        return categoryServices.getListByType(type);
    }

    @GetMapping("/list")
    public ResponseEntity<GlobalResponse<List<CategoryDTO>>> getAllCategories(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return categoryServices.getListPagination(pageNo, pageSize);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse<CategoryDTO>> createCategory(@RequestBody CategoryDTO category) {
        return categoryServices.addCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<CategoryDTO>> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO category) {
        return categoryServices.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<CategoryDTO>> deleteCategory(@PathVariable Integer id) {
        return categoryServices.removeCategory(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<CategoryDTO>> getCategory(@PathVariable Integer id) {
        return categoryServices.getCategory(id);
    }
}
