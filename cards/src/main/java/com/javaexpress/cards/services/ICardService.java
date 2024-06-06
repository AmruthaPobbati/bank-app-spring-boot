package com.javaexpress.cards.services;

import org.springframework.stereotype.Service;

import com.javaexpress.cards.dto.CardDTO;

@Service
public interface ICardService {
	
	void createCard();
	
	CardDTO fetchCard(String mobileNumber);
	
	boolean updateCard(CardDTO cardDTO);
	
	void deactivateCard(String mobileNumber);

}
