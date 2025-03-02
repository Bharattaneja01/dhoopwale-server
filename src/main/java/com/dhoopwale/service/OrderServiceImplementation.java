package com.dhoopwale.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dhoopwale.exception.OrderException;
import com.dhoopwale.model.Address;
import com.dhoopwale.model.Cart;
import com.dhoopwale.model.CartItem;
import com.dhoopwale.model.Order;
import com.dhoopwale.model.OrderItem;
import com.dhoopwale.model.User;
import com.dhoopwale.repository.AddressRepository;
import com.dhoopwale.repository.CartRepository;
import com.dhoopwale.repository.OrderItemRepository;
import com.dhoopwale.repository.OrderRepository;
import com.dhoopwale.repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService{
	
	private CartRepository cartRepository;
	private OrderRepository orderRepository;
	private CartService cartService;
	private ProductService productService;
	private AddressRepository addressRepository;
	private OrderItemService orderItemService;
	private OrderItemRepository orderItemRepository;
	private UserRepository userRepository;
	
	public OrderServiceImplementation(CartRepository cartRepository,
			CartService cartService,
			ProductService productService,
			OrderRepository orderRepository,
			AddressRepository addressRepository,
			OrderItemService orderItemService,
			OrderItemRepository orderItemRepository,
			UserRepository userRepository) {
		
		this.cartService=cartService;
		this.cartRepository=cartRepository;
		this.productService=productService;
		this.orderRepository=orderRepository;
		this.addressRepository=addressRepository;
		this.userRepository=userRepository;
		this.orderItemRepository=orderItemRepository;
		this.orderItemService=orderItemService;
		
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		

		shippingAddress.setUser(user);
		Address address=addressRepository.save(shippingAddress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart=cartService.findUserCart(user.getId());
		List<OrderItem> orderItems=new ArrayList<>();
		
		for(CartItem item:cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItem createdOrderItem=orderItemRepository.save(orderItem);
			
			orderItems.add(createdOrderItem);
		}
		
		Order createdOrder=new Order();
		createdOrder.setUser(user);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Order savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> opt=orderRepository.findById(orderId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("order not exist with id "+orderId);
	}

	@Override
	public List<Order> userOrderHistory(Long userId) {
		List<Order> orders=orderRepository.getUsersOrders(userId);
		return orders;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		return orderRepository.save(order);
	}

	@Override
	public Order canceledOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		

		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		

		Order order=findOrderById(orderId);
		
		orderRepository.deleteById(orderId);
		
	}

}
