package com.dhoopwale.service;

import com.dhoopwale.exception.ProductException;
import com.dhoopwale.model.Cart;
import com.dhoopwale.model.User;
import com.dhoopwale.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}