package com.mastering.spring.ch03aopwithspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StockDao stockDao;

	@Autowired
	private OrderDao orderDao;

	
	public void checkAndPlaceOrder(){
		
		int availableQuantity = stockDao.retrieveStock().getQuantity();

		logger.info("Retrieved Stock - {}", availableQuantity);
		
		if(availableQuantity>0) {
			orderDao.placeOrder(availableQuantity);
		}
		
	}
}