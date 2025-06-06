package com.example.POS.System.repositories;

import com.example.POS.System.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findTop10ByOrderByCreatedAtDesc();

    Page<Product> findAllByOrderByNameDesc(Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.invoiceProducts ip GROUP BY p ORDER BY SUM(ip.quantity) DESC")
    List<Product> findAllOrderBySales();
}

