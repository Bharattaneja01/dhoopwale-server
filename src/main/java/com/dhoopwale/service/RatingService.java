package com.dhoopwale.service;

import java.util.List;

import com.dhoopwale.exception.ProductException;
import com.dhoopwale.model.Rating;
import com.dhoopwale.model.User;
import com.dhoopwale.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req,User user)throws ProductException;
	public List<Rating> getProductsRating(Long productId);

}
