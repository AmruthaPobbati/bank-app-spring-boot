package com.javaexpress.cards.dto;

import com.javaexpress.cards.enums.CardType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDTO {
	private String mobileNumber;
	private String cardNumber;
	private CardType cardType;
	private double totalLimit;
	private double amountUsed;
	private double availableAmount;
}
