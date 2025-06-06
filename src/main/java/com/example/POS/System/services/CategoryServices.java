package com.example.POS.System.services;

import com.example.POS.System.DTO.CategoryDTO;
import com.example.POS.System.enums.CategoryType;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryServices {
    public ResponseEntity<GlobalResponse<List<CategoryDTO>>> getListPagination(int pageNo, int pageSize);
    public ResponseEntity<GlobalResponse<List<CategoryDTO>>> getList();
    public ResponseEntity<GlobalResponse<List<CategoryDTO>>> getListByType(CategoryType type);
    public ResponseEntity<GlobalResponse<CategoryDTO>> getCategory(Integer id);
    public ResponseEntity<GlobalResponse<CategoryDTO>> addCategory(CategoryDTO category);
    public ResponseEntity<GlobalResponse<CategoryDTO>> removeCategory(Integer id);
    public ResponseEntity<GlobalResponse<CategoryDTO>> updateCategory(Integer id, CategoryDTO category);
}
