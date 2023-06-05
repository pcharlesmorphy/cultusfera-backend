package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.ReviewDTO;
import com.example.biblioteca.model.entity.Review;
import com.example.biblioteca.service.ResourceServiceImpl;
import com.example.biblioteca.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapperImpl implements IReviewMapper {

    @Autowired
    UserMapperImpl userMapper;
    @Autowired
    ResourceMapperImpl resourceMapper;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ResourceServiceImpl resourceService;

    @Override
    public Review dtoToEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setDate(reviewDTO.getDate());
        review.setTitle(reviewDTO.getTitle());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setUser(userService.findById(reviewDTO.getUserId()).get());
        review.setResource(resourceService.findResourceById(reviewDTO.getResourceId()).get());
        return review;
    }

    @Override
    public ReviewDTO entityToDto(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setDate(review.getDate());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setUserId(review.getUser().getId());
        reviewDTO.setResourceId(review.getResource().getId());
        reviewDTO.setUsername(review.getUser().getUsername());
        reviewDTO.setResourceTitle(review.getResource().getTitle());
        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> entityToDtoList(List<Review> reviews) {
        List<ReviewDTO> commentsDTO = new ArrayList<ReviewDTO>();
        ReviewDTO reviewDTO = new ReviewDTO();
        for (Review c: reviews){
            reviewDTO =entityToDto(c);
            commentsDTO.add(reviewDTO);
        }
        return commentsDTO;
    }

    @Override
    public List<Review> dtoListToEntity(List<ReviewDTO> commentsDTO) {
        List<Review> reviews = new ArrayList<Review>();
        Review review = new Review();
        for (ReviewDTO c:commentsDTO){
            review =dtoToEntity(c);
            reviews.add(review);
        }
        return reviews;
    }
}
