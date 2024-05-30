package jayslabs.microservicedemo.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jayslabs.microservicedemo.accounts.dto.LoansDTO;

@Component
public class LoansFallback implements LoansFeignClient {

	@Override
	public ResponseEntity<LoansDTO> fetchLoanDetails(String corrId, String mobile) {
		return null;
	}

}
