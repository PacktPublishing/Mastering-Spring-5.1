package com.mastering.spring.ch03aopwithspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StockDao {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Stock retrieveStock(){
		logger.info("Returning a dummy value");
		return new Stock(20);
	}

}