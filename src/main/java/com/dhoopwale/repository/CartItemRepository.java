package com.dhoopwale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhoopwale.model.Cart;
import com.dhoopwale.model.CartItem;
import com.dhoopwale.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{
	
	@Query("SELECT ci From CartItem ci Where ci.cart=:cart And ci.product=product And ci.size=:size And ci.userId=:userId")
	public CartItem iscartItemExist(@Param("cart") Cart cart,@Param("product")Product product,@Param("size")String size,@Param("userId")Long userId);

}
