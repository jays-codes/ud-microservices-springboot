package jayslabs.microservicedemo.cards.mapper;

import jayslabs.microservicedemo.cards.dto.CardsDTO;
import jayslabs.microservicedemo.cards.entity.Cards;

public class CardsMapper {
	public static CardsDTO mapToCardsDTO(Cards card, CardsDTO dto) {
		dto.setMobileNumber(card.getMobileNumber());
		dto.setCardNumber(card.getCardNumber());
		dto.setCardType(card.getCardType());
		dto.setTotalLimit(card.getTotalLimit());
		dto.setAmountUsed(card.getAmountUsed());
		dto.setAvailableAmount(card.getAvailableAmount());
		return dto;
	}
	
	public static Cards mapToCards(CardsDTO dto, Cards card) {
		card.setCardNumber(dto.getCardNumber());
		card.setMobileNumber(dto.getMobileNumber());
		card.setCardType(dto.getCardType());
		card.setTotalLimit(dto.getTotalLimit());
		card.setAmountUsed(dto.getAmountUsed());
		card.setAvailableAmount(dto.getAvailableAmount());
		return card;
	}
}
