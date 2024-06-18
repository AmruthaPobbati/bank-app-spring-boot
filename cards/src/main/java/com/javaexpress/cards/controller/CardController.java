package com.javaexpress.cards.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.cards.dto.CardDTO;
import com.javaexpress.cards.enums.CardType;
import com.javaexpress.cards.services.ICardService;
import com.javaexpress.cards.services.impl.CardService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "cards")
public class CardController {
	
	private ICardService cardService;
	
	@PostMapping("create")
	public String createCard(@RequestParam String mobileNumber, @RequestParam CardType cardType) {
		log.info("CardController :: createCard: {} [{}]", mobileNumber, cardType);
		cardService.createCard(mobileNumber, cardType);
		return "Card Created Successfully !";
	}
	
	@GetMapping("fetchAll")
	public List<CardDTO> getAllCards(@RequestParam String mobileNumber) {
		log.info("CardController :: getAllCards: {}", mobileNumber);
		return cardService.fetchCards(mobileNumber);
	}
	
	@GetMapping("cardDetails")
	public CardDTO getCardDetails(@RequestParam String cardNumber) {
		log.info("CardController :: getCardDetails: {}", cardNumber);
		return cardService.fetchCard(cardNumber);
	}
	
	@PutMapping("updateDetails")
	public boolean updateCardDetails(@RequestBody CardDTO cardDTO) {
		log.info("CardController :: updateCardDetails: {}", cardDTO.getCardNumber());
		return cardService.updateCardDetails(cardDTO);
	}
	
	@PutMapping("deactivate")
	public boolean deactivateCard(@RequestParam String cardNumber) {
		log.info("CardController :: deactivateCard: {}", cardNumber);
		return cardService.deactivateCard(cardNumber);
	}
	
	@PutMapping("deactivateAll")
	public void deactivateAllCards(@RequestParam String mobileNumber) {
		log.info("CardController :: deactivateAllCards: {}", mobileNumber);
		cardService.deactivateAllCards(mobileNumber);
	}
	
	@DeleteMapping("delete")
	public void deleteCard(@RequestParam String mobileNumber, @RequestParam CardType cardType) {
		log.info("CardController :: deleteCard: {} [{}]", mobileNumber, cardType);
		cardService.deleteCard(mobileNumber, cardType);
	}

}
