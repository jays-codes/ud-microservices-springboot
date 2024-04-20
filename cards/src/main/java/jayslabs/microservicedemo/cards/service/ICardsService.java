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
	
	/**
	 * @param dto - CardsDTO object
	 * @return boolean - whether or not Update Card is successful
	 **/
	boolean updateCard(CardsDTO dto);
	
	/**
	 * @param mobile- Mobile Number
	 * @return boolean - whether or not Delete Card is successful
	 **/
	boolean deleteCard(String mobile);
}
