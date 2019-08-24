package com.silverbar.marketplace.silverbarmarketplace.service;

import java.util.List;
import java.util.Optional;

import com.silverbar.marketplace.silverbarmarketplace.model.Order;

public interface OrderService {

	Order saveOrder(Order oder);
	
	List<Order> findAllOrders();
	
	List<String> orderSummary();
	
	void cancelOrder(Integer id);

	Optional<Order> findById(Integer id);
}
