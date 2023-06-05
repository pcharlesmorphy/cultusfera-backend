package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Resource;
import com.example.biblioteca.model.entity.Review;
import com.example.biblioteca.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IReviewService {
    List<Review> findAll ();
    Optional<Review> save(Review review);
    Boolean delete(Long id);
    Optional<Review> update (Review review);
    Optional<Review> findById (Long id);

    List<Review> findAllByResource (Long id);
    List<Review> findAllByUser (Long id);


}
