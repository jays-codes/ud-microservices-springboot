package jayslabs.microservicedemo.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jayslabs.microservicedemo.accounts.dto.CardsDTO;

@Component
public class CardsFallback implements CardsFeignClient {

	@Override
	public ResponseEntity<CardsDTO> fetchCardDetails(String corrId, String mobile) {
		return null;
	}

}
