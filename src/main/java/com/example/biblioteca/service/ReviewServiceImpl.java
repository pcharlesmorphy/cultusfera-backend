package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Book;
import com.example.biblioteca.model.entity.Resource;
import com.example.biblioteca.model.entity.Review;
import com.example.biblioteca.model.entity.User;
import com.example.biblioteca.repository.IResourceRepository;
import com.example.biblioteca.repository.IReviewRepository;
import com.example.biblioteca.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements IReviewService {

    @Autowired
    IReviewRepository reviewRepo;

    @Autowired
    IResourceRepository resourceRepo;

    @Autowired
    IUserRepository userRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    @Override
    @Transactional
    public Optional<Review> save(Review review) {
        if (!checkDuplicatedUserReviewForResource(review)){
            return Optional.of(reviewRepo.save(review));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Review> update(Review review) {
        if (!checkDuplicatedUserReviewForResource(review)){
           return Optional.of(reviewRepo.save(review));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedUserReviewForResource (Review review){

        Long currentReviewResourceId = review.getResource().getId();
        User currentReviewUser = review.getUser();
        List<Review> userReviews = reviewRepo.findAllByUser(currentReviewUser);

        if (userReviews.isEmpty()){
            return false;
        }
        for (Review r:userReviews){
            if (r.getResource().getId().equals(currentReviewResourceId)){
                return true;
            }
        }

        return false;

    }

    @Override
    public Boolean delete(Long id) {
        if (findById(id).isEmpty()) return false;
        reviewRepo.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Review> findById(Long id) {
        return Optional.ofNullable(reviewRepo.findById(id)).orElse(null);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Review> findAllByResource(Long id) {

        Resource resource = resourceRepo.findById(id).get();
        return reviewRepo.findAllByResource(resource);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Review> findAllByUser(Long id) {

        User user = userRepo.findById(id).get();
        return reviewRepo.findAllByUser(user);
    }

}


