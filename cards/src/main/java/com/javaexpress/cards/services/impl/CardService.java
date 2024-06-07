package com.javaexpress.cards.services.impl;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.javaexpress.cards.dto.CardDTO;
import com.javaexpress.cards.entity.Card;
import com.javaexpress.cards.enums.CardType;
import com.javaexpress.cards.exceptions.CardExceptionType;
import com.javaexpress.cards.exceptions.CardsRunTimeException;
import com.javaexpress.cards.repository.CardRepository;
import com.javaexpress.cards.services.ICardService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CardService implements ICardService{

	private CardRepository cardRepository;
	
	@Override
	public void createCard(String mobileNumber, CardType cardType) {
		log.info("CardService :: createCard");
		Optional<Card> optionalCard = cardRepository.findByMobileNumberAndCardTypeAndIsActiveTrue(mobileNumber, cardType);
		
		if (optionalCard.isPresent()) {
			throw new CardsRunTimeException(CardExceptionType.CARD_EXISTS_EXCEPTION);
		}
		
		cardRepository.save(generateNewCard(mobileNumber, cardType));
		log.info("Card Created Successfully");
	}
	
	private Card generateNewCard(String mobileNumber, CardType cardType) {
		log.info("CardService :: generateNewCard");
		Long cardNumber = 1000000000000000L + new Random().nextLong(999999999999999L);
		Integer pin = new Random().nextInt(9999);
		
		Card card = Card.builder()
						.cardNumber(String.valueOf(cardNumber))
						.mobileNumber(mobileNumber)
						.cardType(cardType)
						.isActive(true)
						.pin(pin)
						.totalLimit(cardType.getLimit())
						.amountUsed(0)
						.build();
		
		return card;
	}

	@Override
	public List<CardDTO> fetchCards(String mobileNumber) {
		log.info("CardService :: fetchCards");
		Optional<List<Card>> optionalCard = cardRepository.findByMobileNumberAndIsActiveTrue(mobileNumber);
		List<Card> listofActiveCards = optionalCard.get();
		
		return listofActiveCards.stream().map((dbCard) -> CardDTO.builder()
				.mobileNumber(dbCard.getMobileNumber())
				.cardNumber(dbCard.getCardNumber())
				.cardType(dbCard.getCardType())
				.totalLimit(dbCard.getTotalLimit())
				.amountUsed(dbCard.getAmountUsed())
				.availableAmount(dbCard.getTotalLimit() - dbCard.getAmountUsed())
				.build()).collect(Collectors.toList());
	}
	

	@Override
	public CardDTO fetchCard(String cardNumber) {
		log.info("CardService :: fetchCard");
		Card dbCard = cardRepository.findByCardNumber(cardNumber)
				.orElseThrow(() -> new CardsRunTimeException(CardExceptionType.CARD_NOT_EXISTS_EXCEPTION));
		
		CardDTO cardDTO = CardDTO.builder()
									.mobileNumber(dbCard.getMobileNumber())
									.cardNumber(dbCard.getCardNumber())
									.cardType(dbCard.getCardType())
									.totalLimit(dbCard.getTotalLimit())
									.amountUsed(dbCard.getAmountUsed())
									.availableAmount(dbCard.getTotalLimit() - dbCard.getAmountUsed())
									.build();
		return cardDTO;
	}

	@Override
	public boolean updateCardDetails(CardDTO cardDTO) {
		log.info("CardService :: updateCardDetails");
		boolean isUpdated = false;
		
		Card dbCard = cardRepository.findByCardNumber(cardDTO.getCardNumber())
				.orElseThrow(() -> new CardsRunTimeException(CardExceptionType.CARD_NOT_EXISTS_EXCEPTION));
		
		if (!dbCard.getIsActive()) {
			isUpdated = false;
		} else { 
			dbCard.setMobileNumber(cardDTO.getMobileNumber());
			dbCard.setAmountUsed(cardDTO.getAmountUsed());
			
			cardRepository.save(dbCard);
			isUpdated = true;
		}
		log.info("CardService :: updateCardDetails :: "+ isUpdated);
		return isUpdated;
	}

	@Override
	public boolean deactivateCard(String cardNumber) {
		log.info("CardService :: deactivateCard");
		boolean isUpdated = false;
		
		Card dbCard = cardRepository.findByCardNumber(cardNumber)
				.orElseThrow(() -> new CardsRunTimeException(CardExceptionType.CARD_NOT_EXISTS_EXCEPTION));
		
		if (!dbCard.getIsActive()) {
			isUpdated = false;
		} else { 
			dbCard.setIsActive(false);
			cardRepository.save(dbCard);
			
			isUpdated = true;
		}
	
		return isUpdated;
	}

	@Override
	public boolean deactivateAllCards(String mobileNumber) {
		log.info("CardService :: deactivateAllCards");
		boolean isUpdated = false;
		
		Optional<List<Card>> optionalActiveCards = cardRepository.findByMobileNumberAndIsActiveTrue(mobileNumber);
		
		if (!optionalActiveCards.isEmpty()) {
			isUpdated = false;
		} else { 
			List<Card> activeCards = optionalActiveCards.get();
			activeCards.stream().forEach(card -> card.setIsActive(false));

			cardRepository.saveAll(activeCards);
			isUpdated = true;
		}
	
		return isUpdated;
		
	}

	@Override
	public void deleteCard(String mobileNumber, CardType cardType) {
		log.info("CardService :: deleteCard");
		Optional<Card> optionalCard = cardRepository.findByMobileNumberAndCardType(mobileNumber, cardType);
		
		cardRepository.delete(optionalCard.get());
		
	}
	
	

}
