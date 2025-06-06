package com.example.POS.System.services;

import com.example.POS.System.DTO.CategoryDTO;
import com.example.POS.System.DTO.ProductDTO;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductServices {
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> listByCategoryId(Integer categoryId);
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> listFavorite(Integer user, Integer product, String sortOrder, String name);
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> addFavorite(Integer user, Integer product);
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> removeFavorite(Integer user, Integer product);
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getListPagination(int pageNo, int pageSize);
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getSale();
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getLatest();
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getList();
    public ResponseEntity<GlobalResponse<ProductDTO>> getOne(Integer id);
    public ResponseEntity<GlobalResponse<ProductDTO>> create(ProductDTO request);
    public ResponseEntity<GlobalResponse<ProductDTO>> delete(Integer id);
    public ResponseEntity<GlobalResponse<ProductDTO>> update(Integer id, ProductDTO request);
}
