package com.javaexpress.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	@Column(name = "mobile_number")
	private String mobileNumber;
	private String cardNumber;
	private String cardType;
	private String pin;
	private double totalLimit;
	private double amountUsed;
	
	public double getAvailableAmount() {
		return totalLimit - amountUsed;
	}
}
