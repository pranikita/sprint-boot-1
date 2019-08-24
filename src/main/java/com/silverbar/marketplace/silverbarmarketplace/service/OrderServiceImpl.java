package com.silverbar.marketplace.silverbarmarketplace.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverbar.marketplace.silverbarmarketplace.constants.OrderType;
import com.silverbar.marketplace.silverbarmarketplace.model.Order;
import com.silverbar.marketplace.silverbarmarketplace.repository.OrdertRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdertRepository orderRepository;
	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void cancelOrder(Integer id) {
		orderRepository.deleteById(id);
	}

	@Override
	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}
	
	@Override
	public Optional<Order> findById(Integer id) {
		return orderRepository.findById(id);
	}
	
	@Override
	public List<String> orderSummary() {
		
		Optional<List<Order>> orderList = Optional.ofNullable(orderRepository.findAll());
		List<String> consolidatedOrderSummaryList = new ArrayList<String>();
		if(orderList.isPresent()) {
			Map<String,List<Order>> ordersMapGroupByOrderType = getOrdersByOrderType(orderList);
			
			Map<BigDecimal,List<Order>> sellOrdersMapGroupByPrice = getOrdersByPrice(Optional.ofNullable(ordersMapGroupByOrderType.get(OrderType.SELL.toString())),true);
			Map<BigDecimal,List<Order>> buyOrdersMapGroupByPrice = getOrdersByPrice(Optional.ofNullable(ordersMapGroupByOrderType.get(OrderType.BUY.toString())),false);
			
			
			consolidatedOrderSummaryList.addAll(getOrderSummary(sellOrdersMapGroupByPrice));
			consolidatedOrderSummaryList.addAll(getOrderSummary(buyOrdersMapGroupByPrice));
		}
		return consolidatedOrderSummaryList;
	}

	private Collection<? extends String> getOrderSummary(Map<BigDecimal, List<Order>> ordersMap) {
		
		Collection<List<Order>> list = ordersMap.values();
		
		List<String> displaySummaryList = new ArrayList<String>();
		for(List<Order> orderList: list) {
			StringBuffer buffer = getOrderSummaryItem(orderList);
			displaySummaryList.add(buffer.toString());
		}
		return displaySummaryList;
	}

	
	private StringBuffer getOrderSummaryItem(List<Order> orderList) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(orderList.stream().map(Order::getQuantity).mapToDouble(Double::doubleValue).sum()).
		append(" ").
		append(orderList.stream().findFirst().get().getUnitType()).
		append(" ").
		append("for").
		append(" ").
		append("£").
		append(orderList.stream().findFirst().get().getPrice());
		return buffer;
	}

	private Map<BigDecimal, List<Order>> getOrdersByPrice(Optional<List<Order>> orderList, boolean isOrderByAscending) {
		Map<BigDecimal, List<Order>> sortedMap = new LinkedHashMap<BigDecimal, List<Order>>();
		
		if(orderList.isPresent()) {
			
			Map<BigDecimal, List<Order>> unSortedOrderMap = orderList.get().stream().collect(Collectors.groupingBy(Order::getPrice));
			
			if(isOrderByAscending)
				unSortedOrderMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x->sortedMap.put(x.getKey(), x.getValue()));
			else
				unSortedOrderMap.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEachOrdered(x->sortedMap.put(x.getKey(), x.getValue()));
		}
		
		return sortedMap;
	}

	private Map<String, List<Order>> getOrdersByOrderType(Optional<List<Order>> orderList) {
		Map<String, List<Order>> ordersGroupByOrderType = orderList.get().stream().collect(Collectors.groupingBy(Order::getOrderType));
		return ordersGroupByOrderType;
	}
	
}
