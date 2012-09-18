package com.mycompany;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private Date orderDate;
	private ORDER_STATUS status;
	
	public Date getOrderDate() {
		return orderDate;
	}

	public ORDER_STATUS getStatus() {
		return status;
	}

	public Order(Date orderDate, ORDER_STATUS status) {
		super();
		this.orderDate = orderDate;
		this.status = status;
	}
}
