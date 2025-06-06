package com.example.POS.System.DTO;

import com.example.POS.System.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDTO {
    private Integer id;
    private String name;
    private CategoryType type;
    private String image;
}