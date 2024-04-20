package jayslabs.microservicedemo.cards.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import jayslabs.microservicedemo.cards.constants.CardsConstants;
import jayslabs.microservicedemo.cards.entity.Cards;
import jayslabs.microservicedemo.cards.exception.CardsAlreadyExistsException;
import jayslabs.microservicedemo.cards.repository.CardsRepository;
import jayslabs.microservicedemo.cards.service.ICardsService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

	private CardsRepository repo;
	
	@Override
	public void createCard(String mobile) {
		if (repo.findByMobileNumber(mobile).isPresent()) {
			throw new CardsAlreadyExistsException("Card already registered with given mobile number " 
					+ mobile);
		}
		repo.save(createNewCard(mobile));

	}
	
	private Cards createNewCard(String mobile) {
		Cards card = new Cards();
		card.setMobileNumber(mobile);
		Long randnum = 100000000000L + new Random().nextInt(900000000);
		card.setCardNumber(randnum.toString());
		card.setCardType(CardsConstants.Credit);
		card.setTotalLimit(0);
		card.setAmountUsed(0);
		card.setAvailableAmount(CardsConstants.INIT_TOTAL);
		return card;
	}

}
