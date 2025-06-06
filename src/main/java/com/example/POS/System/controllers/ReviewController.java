package com.example.POS.System.controllers;

import com.example.POS.System.DTO.ReviewDTO;
import com.example.POS.System.DTO.StaticReview;
import com.example.POS.System.DTO.UserDTO;
import com.example.POS.System.services.ReviewServices;
import com.example.POS.System.utils.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewServices reviewServices;

    @GetMapping("/{productId}")
    public ResponseEntity<GlobalResponse<List<ReviewDTO>>> getAll(@PathVariable Integer productId) {
        return reviewServices.getList(productId);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse<ReviewDTO>> create(@RequestBody ReviewDTO data) {
        return reviewServices.create(data);
    }

    @GetMapping("/static/{productId}")
    public ResponseEntity<GlobalResponse<StaticReview>> getStatic(@PathVariable Integer productId) {
        return reviewServices.getReviewStatistics(productId);
    }

}
