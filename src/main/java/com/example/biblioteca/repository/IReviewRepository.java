package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Resource;
import com.example.biblioteca.model.entity.Review;
import com.example.biblioteca.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByResource (Resource resource);
    List<Review> findAllByUser (User user);
}
