package com.example.POS.System.services;

import com.example.POS.System.DTO.ReviewDTO;
import com.example.POS.System.DTO.StaticReview;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewServices {
    public ResponseEntity<GlobalResponse<ReviewDTO>> create(ReviewDTO review);
    public ResponseEntity<GlobalResponse<List<ReviewDTO>>> getList(Integer productId);
    public ResponseEntity<GlobalResponse<StaticReview>> getReviewStatistics(Integer productId);
}
