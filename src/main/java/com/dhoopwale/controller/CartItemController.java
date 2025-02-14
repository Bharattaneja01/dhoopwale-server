package com.dhoopwale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhoopwale.exception.CartItemException;
import com.dhoopwale.exception.ProductException;
import com.dhoopwale.exception.UserException;
import com.dhoopwale.model.CartItem;
import com.dhoopwale.model.Product;
import com.dhoopwale.model.User;
import com.dhoopwale.response.ApiResponse;
import com.dhoopwale.service.CartItemService;
import com.dhoopwale.service.UserService;

@RestController
@RequestMapping("api/cart_items")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
//	@Operation(description="remove cart item from cart")
//	@io.swagger.v3.oas.annotations.responses.ApiResponse(description="Delete Item")
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt)throws UserException,CartItemException{
		User user=userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse res=new ApiResponse();
		res.setMessage("item deleted from cart");
		res.setStatus(true);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/{cartItemId}")
//	@Operation(description="Update Item To Cart")
	public ResponseEntity<CartItem> updateCartItem(
			@RequestBody CartItem cartItem,
			@PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartItemException{
		User user=userService.findUserProfileByJwt(jwt);
		
		CartItem updatedCartItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
		
		return new ResponseEntity<>(updatedCartItem,HttpStatus.OK);
	}
	

}
