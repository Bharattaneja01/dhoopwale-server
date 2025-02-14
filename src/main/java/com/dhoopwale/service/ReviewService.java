package com.dhoopwale.service;

import java.util.List;

import com.dhoopwale.exception.ProductException;
import com.dhoopwale.model.Review;
import com.dhoopwale.model.User;
import com.dhoopwale.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user)throws ProductException;
	
	public List<Review>getAllReview(Long productId);
}
