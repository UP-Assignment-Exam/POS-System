package com.example.POS.System.servicesImp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.POS.System.DTO.ReviewDTO;
import com.example.POS.System.DTO.StaticReview;
import com.example.POS.System.exceptions.Custom.NotFoundException;
import com.example.POS.System.utils.Utils;
import com.example.POS.System.models.Product;
import com.example.POS.System.models.Review;
import com.example.POS.System.models.User;
import com.example.POS.System.repositories.GlobalRepository;
import com.example.POS.System.services.ReviewServices;
import com.example.POS.System.utils.GlobalResponse;

@Service
public class ReviewServicesImp implements ReviewServices {

    @Autowired
    private GlobalRepository db;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<GlobalResponse<List<ReviewDTO>>> getList(Integer productId) {
        List<Review> reviews = db.reviews.findByProductId(productId);

        List<ReviewDTO> reviewDTO = db.mapper.map(reviews, new TypeToken<List<ReviewDTO>>() {
        }.getType());

        return Utils.Success("Successfully", reviewDTO);
    }

    @Override
    public ResponseEntity<GlobalResponse<ReviewDTO>> create(ReviewDTO review) {
        User user = db.users.findById(review.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        Product product = db.products.findById(review.getProductId()).orElseThrow(() -> new NotFoundException("Product not found"));

        Review data = new Review();
        data.setStar(review.getStar());
        data.setComment(review.getComment());
        data.setUser(user);
        data.setProduct(product);

        double productReviewsCount = product.getReviews().size();
        double productRating = product.getRating();
        double newRating = (productRating * productReviewsCount + review.getStar()) / (productReviewsCount + 1);

        product.setRating(newRating);

        db.products.save(product);
        Review rsp = db.reviews.save(data);

        review = db.mapper.map(rsp, ReviewDTO.class);
        return Utils.Success("Successfully", review);
    }

    @Override
    public ResponseEntity<GlobalResponse<StaticReview>> getReviewStatistics(Integer productId) {
        StaticReview response = new StaticReview();

        Double averageRating = db.reviews.findAverageRatingByProductId(productId);
        response.setAverageRating(averageRating);

        response.setStar5(db.reviews.countReviewsByStarRating(productId, 5.0));
        response.setStar4(db.reviews.countReviewsByStarRating(productId, 4.0));
        response.setStar3(db.reviews.countReviewsByStarRating(productId, 3.0));
        response.setStar2(db.reviews.countReviewsByStarRating(productId, 2.0));
        response.setStar1(db.reviews.countReviewsByStarRating(productId, 1.0));
        response.setTotal(db.reviews.countReviews(productId));

        return Utils.Success("Successfully", response);
    }
}
