package com.javaexpress.cards.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.cards.dto.CardDTO;
import com.javaexpress.cards.entity.Card;
import com.javaexpress.cards.enums.CardType;

public interface CardRepository extends JpaRepository<Card, Long>{
	
	Optional<Card> findByMobileNumberAndCardType(String mobileNumber, CardType cardType);
	
	Optional<Card> findByMobileNumberAndCardTypeAndIsActiveTrue(String mobileNumber, CardType cardType);
	
	Optional<List<Card>> findByMobileNumberAndIsActiveTrue(String mobileNumber);
	
	Optional<Card> findByCardNumber(String cardNumber);

	Optional<Card> findByCardNumberAndIsActiveTrue(String cardNumber);

}
