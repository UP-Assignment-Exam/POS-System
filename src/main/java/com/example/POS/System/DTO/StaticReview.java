package com.example.POS.System.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaticReview {
    private Long Total;
    private Double averageRating;
    private Long Star5;
    private Long Star4;
    private Long Star3;
    private Long Star2;
    private Long Star1;
}
