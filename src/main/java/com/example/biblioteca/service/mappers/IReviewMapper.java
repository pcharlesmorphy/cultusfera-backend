package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.ReviewDTO;
import com.example.biblioteca.model.entity.Review;

import java.util.List;

public interface IReviewMapper {
    Review dtoToEntity (ReviewDTO reviewDTO);
    ReviewDTO entityToDto (Review review);
    List<ReviewDTO> entityToDtoList (List<Review> reviews);
    List<Review> dtoListToEntity (List<ReviewDTO> commentsDTO);
}
