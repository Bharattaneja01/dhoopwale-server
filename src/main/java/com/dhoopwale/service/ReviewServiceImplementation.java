package com.dhoopwale.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dhoopwale.exception.ProductException;
import com.dhoopwale.model.Product;
import com.dhoopwale.model.Review;
import com.dhoopwale.model.User;
import com.dhoopwale.repository.ProductRepository;
import com.dhoopwale.repository.ReviewRepository;
import com.dhoopwale.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	public ReviewServiceImplementation(ReviewRepository reviewRepository,
			ProductService productService,
			ProductRepository productRepository) {
		
//		this.productRepository=productRepository;
		this.productService=productService;
		this.reviewRepository=reviewRepository;
		
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}
	
	

}
