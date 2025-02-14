package com.dhoopwale.model;

import jakarta.persistence.Column;

public class PaymentInformation {
	
	@Column(name="cardholder_name")
	private String cardholdetName;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expiration_date")
	private String expirationDate;
	
	@Column(name="cvv")
	private String cvv;

}
