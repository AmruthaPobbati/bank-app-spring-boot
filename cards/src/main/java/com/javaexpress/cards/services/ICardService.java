package com.javaexpress.cards.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaexpress.cards.dto.CardDTO;
import com.javaexpress.cards.enums.CardType;


@Service
public interface ICardService {

	void createCard(String mobileNumber, CardType cardType);
	
	public List<CardDTO> fetchCards(String mobileNumber);
	
	public CardDTO fetchCard(String cardNumber);

	public boolean updateCardDetails(CardDTO cardDTO);

	public boolean deactivateCard(String cardNumber);
	
	public boolean deactivateAllCards(String mobileNumber);
	
	public void deleteCard(String mobileNumber, CardType cardType);


}
