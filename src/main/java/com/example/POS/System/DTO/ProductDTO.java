package com.example.POS.System.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

    private Integer id;
    private String name;
    private String description;
    private Double discount;
    private Double price;
    private Integer quantity;
    private List<String> images;
    private List<String> sizes;
    private List<String> colors;
    private Integer categoryId;
    private String categoryName;
    private Double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isFavorite;
}
