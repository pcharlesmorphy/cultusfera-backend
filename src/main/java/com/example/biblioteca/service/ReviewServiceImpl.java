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
            Resource resource = calculateReviewAverageRating(review,"add");
            resourceRepo.save(resource);
            return Optional.of(reviewRepo.save(review));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Review> update(Review review) {

        Review oldReview = reviewRepo.findById(review.getId()).get();
        Resource resource = calculateReviewAverageRating(oldReview,review);
        resourceRepo.save(resource);
        return Optional.of(reviewRepo.save(review));

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
        Review deletedReview = reviewRepo.getReferenceById(id);
        Resource resource = calculateReviewAverageRating(deletedReview,"delete");
        resourceRepo.save(resource);
        reviewRepo.deleteById(id);
        return true;
    }

    private Resource calculateReviewAverageRating (Review review, String operation){

        Resource resource= resourceRepo.findById(review.getResource().getId()).get();
        Double resourceAverageRating = resource.getRating();
        Integer resourceTotalReviews = resource.getTotalReviews();
        Integer resourceTotalRating = (int) (resourceAverageRating * resourceTotalReviews);
        Integer newResourceTotalRating=0;

        if (operation.equals("add")){
            newResourceTotalRating = resourceTotalRating + review.getRating();
            resourceTotalReviews++;
        } else if (operation.equals("delete")){
            newResourceTotalRating = resourceTotalRating - review.getRating();
            resourceTotalReviews--;
        }

        Double newResourceAverageRating = (double) newResourceTotalRating / resourceTotalReviews;
        resource.setRating(newResourceAverageRating);
        resource.setTotalReviews(resourceTotalReviews);
        return resource;
    }

    private Resource calculateReviewAverageRating (Review oldReview, Review newReview){
        Resource resource= resourceRepo.findById(oldReview.getResource().getId()).get();
        Double resourceAverageRating = resource.getRating();
        Integer resourceTotalReviews = resource.getTotalReviews();
        Integer resourceTotalRating = (int) (resourceAverageRating * resourceTotalReviews);

        resourceTotalRating = resourceTotalRating - oldReview.getRating();
        Integer newResourceTotalRating = resourceTotalRating + newReview.getRating();
        Double newResourceAverageRating = (double) newResourceTotalRating / resource.getTotalReviews();
        resource.setRating(newResourceAverageRating);
        return resource;
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


