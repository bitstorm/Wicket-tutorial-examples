package com.mycompany;

public enum ORDER_STATUS {
	PAYMENT_ACCEPTED(0),
	IN_PROGRESS(1),
	SHIPPING(2),
	DELIVERED(3);
	
	private int code;
	
	public int getCode() {
		return code;
	}

	private ORDER_STATUS(int code){
		this.code = code;
	}
}
