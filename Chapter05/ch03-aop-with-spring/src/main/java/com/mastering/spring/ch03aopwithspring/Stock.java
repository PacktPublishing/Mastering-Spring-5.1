package com.mastering.spring.ch03aopwithspring;

public class Stock {
	private int quantity;

	public Stock(int quantity) {
		super();
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Stock [quantity=" + quantity + "]";
	}


}
