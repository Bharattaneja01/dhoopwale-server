package com.dhoopwale.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dhoopwale.exception.ProductException;
import com.dhoopwale.model.Product;
import com.dhoopwale.model.Rating;
import com.dhoopwale.model.User;
import com.dhoopwale.repository.RatingRepository;
import com.dhoopwale.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService{
	
	private RatingRepository ratingRepository;
	private ProductService productService;
	
	public RatingServiceImplementation(RatingRepository ratingRepository,
			ProductService productService) {
		this.productService=productService;
		this.ratingRepository=ratingRepository;
		
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		
		Rating rating=new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		return ratingRepository.getAllProductsRating(productId);
	}

}
