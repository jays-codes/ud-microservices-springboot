package jayslabs.microservicedemo.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import jayslabs.microservicedemo.accounts.dto.CardsDTO;

@FeignClient("cards")
public interface CardsFeignClient {

	@GetMapping(value="/api/fetch", consumes="application/json")
	public ResponseEntity<CardsDTO> fetchCardDetails(
			@RequestHeader("jayslabs-correlation-id") String corrId, 			
			@RequestParam String mobile);
	
}
