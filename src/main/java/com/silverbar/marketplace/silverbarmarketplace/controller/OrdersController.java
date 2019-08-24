package com.silverbar.marketplace.silverbarmarketplace.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silverbar.marketplace.silverbarmarketplace.model.Order;
import com.silverbar.marketplace.silverbarmarketplace.service.OrderService;

@RestController
@RequestMapping("v1/orders")
public class OrdersController {

	@Autowired
	private OrderService orderService;
	
	private Map<String,String> messageMap;
	
	@PostMapping
	public ResponseEntity<?> saveAccount(@Valid @RequestBody Order order) {
		order = orderService.saveOrder(order);
		messageMap = new HashMap<>();
		messageMap.put("message", ("order created "));
		return new ResponseEntity<>(messageMap,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> getOrders() {
		
		List<Order> orders = orderService.findAllOrders();
				
		return ResponseEntity.ok(orders);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id") int id) {

		Optional<Order> order = orderService.findById(id);
		messageMap = new HashMap<>();
		if(!order.isPresent()){
			messageMap.put("message",("No order found "));
			return new ResponseEntity<>(messageMap,HttpStatus.NO_CONTENT);
		}
		orderService.cancelOrder(id);
		messageMap.put("message",("order deleted "));
		return new ResponseEntity<>(messageMap,HttpStatus.ACCEPTED);
    }
	
	@GetMapping("/summary")
	public ResponseEntity<List<String>> getOrderSummary() {
		
		List<String> orders = orderService.orderSummary();
				
		return ResponseEntity.ok(orders);
	}
	
	
}
