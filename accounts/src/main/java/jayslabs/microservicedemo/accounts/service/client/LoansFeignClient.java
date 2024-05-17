package jayslabs.microservicedemo.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jayslabs.microservicedemo.accounts.dto.LoansDTO;

@FeignClient("loans")
public interface LoansFeignClient {


	@GetMapping(value="/api/fetch", consumes="application/json")
	public ResponseEntity<LoansDTO> fetchLoanDetails(@RequestParam String mobile);
	
}
