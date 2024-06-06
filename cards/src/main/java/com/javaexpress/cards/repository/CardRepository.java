package com.javaexpress.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.cards.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

}
