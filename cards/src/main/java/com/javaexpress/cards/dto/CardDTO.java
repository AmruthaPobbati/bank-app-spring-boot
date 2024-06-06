package com.javaexpress.cards.dto;

import lombok.Data;

@Data
public class CardDTO {
	private Long cardId;
	private String mobileNumber;
	private String cardNumber;
	private String cardType;
	private String pin;
	private double totalLimit;
	private double amountUsed;
}
