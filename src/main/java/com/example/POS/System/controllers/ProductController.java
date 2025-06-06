package com.example.POS.System.controllers;

import com.example.POS.System.DTO.CategoryDTO;
import com.example.POS.System.DTO.ProductDTO;
import com.example.POS.System.services.ProductServices;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServices productServices;

    @GetMapping()
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getList() {
        return productServices.getList();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> listByCategory(
            @PathVariable Integer categoryId) {
        return productServices.listByCategoryId(categoryId);
    }

    @GetMapping("/favorite/list")
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> listAllFavorites(
            @RequestParam Integer userId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(required = false) String name) {
        return productServices.listFavorite(userId, categoryId, sortOrder, name);
    }

    @PostMapping("/favorite/add/{userId}/{productId}")
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> addFavorite(@PathVariable Integer userId, @PathVariable Integer productId) {
        return productServices.addFavorite(userId, productId);
    }

    @DeleteMapping("/favorite/add/{userId}/{productId}")
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> removeFavorite(@PathVariable Integer userId, @PathVariable Integer productId) {
        return productServices.removeFavorite(userId, productId);
    }

    @GetMapping("/list")
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getAllCategories(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return productServices.getListPagination(pageNo, pageSize);
    }

    @GetMapping("/latest")
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getLatestList() {
        return productServices.getLatest();
    }

    @GetMapping("/sale")
    public ResponseEntity<GlobalResponse<List<ProductDTO>>> getSaleList() {
        return productServices.getSale();
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse<ProductDTO>> create(@RequestBody ProductDTO product) {
        return productServices.create(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<ProductDTO>> update(@PathVariable Integer id, @RequestBody ProductDTO product) {
        return productServices.update(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<ProductDTO>> delete(@PathVariable Integer id) {
        return productServices.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<ProductDTO>> getOne(@PathVariable Integer id) {
        return productServices.getOne(id);
    }
}
