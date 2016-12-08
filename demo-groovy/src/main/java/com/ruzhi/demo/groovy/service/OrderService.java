package com.ruzhi.demo.groovy.service;

import com.ruzhi.demo.groovy.model.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
	private Map<Integer, Order> orderMap;

	@PostConstruct
	public void init() {
		orderMap = new HashMap<Integer, Order>();

		Order order = new Order(1, "iPhone");
		orderMap.put(order.getId(), order);

		order = new Order(2, "iPad");
		orderMap.put(order.getId(), order);
	}

	public Order findOrder(int id) {
		return orderMap.get(id);
	}
}
