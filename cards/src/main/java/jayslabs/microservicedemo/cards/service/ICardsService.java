package jayslabs.microservicedemo.cards.service;

import jayslabs.microservicedemo.cards.dto.CardsDTO;

public interface ICardsService {
	/**
	 * @param mobile - String
	 **/
	void createCard(String mobile);
	
	/**
	 * @param mobile - String
	 **/
	CardsDTO fetchCard (String mobile);
}
