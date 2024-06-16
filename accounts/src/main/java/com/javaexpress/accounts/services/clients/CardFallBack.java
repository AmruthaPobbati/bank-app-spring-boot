package com.javaexpress.accounts.services.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javaexpress.accounts.dto.CardDTO;

@Component
public class CardFallBack implements CardFeignClient {

	@Override
	public List<CardDTO> getAllCards(String mobileNumber) {
		return null;
	}

	@Override
	public void deactivateAllCards(String mobileNumber) {
		// TODO Auto-generated method stub
		
	}

}
