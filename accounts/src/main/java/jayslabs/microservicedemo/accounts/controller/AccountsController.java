package jayslabs.microservicedemo.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jayslabs.microservicedemo.accounts.constants.AccountsConstants;
import jayslabs.microservicedemo.accounts.dto.CustomerDTO;
import jayslabs.microservicedemo.accounts.dto.ResponseDTO;
import jayslabs.microservicedemo.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="/api", produces= {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {
	
	private IAccountsService acctsrvc;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO custdto){
		
		acctsrvc.createAccount(custdto);
		
		ResponseDTO respdto = new ResponseDTO(
				AccountsConstants.STATUS_201,
				AccountsConstants.MESSAGE_201);
				
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(respdto);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam String mobile){
		CustomerDTO custdto = acctsrvc.fetchAccount(mobile);
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(custdto);
	}
}
