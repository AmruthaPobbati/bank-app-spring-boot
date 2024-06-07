package com.javaexpress.cards.entity;

import com.javaexpress.cards.enums.CardType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	
	@Column(name = "mobile_number", nullable = false)
	private String mobileNumber;
	
	@Column(nullable = false)
	private String cardNumber;
	
	@Column(name = "card_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private CardType cardType;
	
	@Column(name = "card_active", nullable = false)
	private Boolean isActive;
	
	private Integer pin;
	
	@Column(name = "total_limit")
	private double totalLimit;
	
	@Column(name = "amount_used")
	private double amountUsed;
}
