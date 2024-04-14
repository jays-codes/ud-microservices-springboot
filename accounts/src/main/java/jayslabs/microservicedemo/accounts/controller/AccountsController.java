package jayslabs.microservicedemo.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {
	
	@GetMapping(path="/hworld")
	public String getHWorld() {
		return "JAYS MICROSERVICES!!!! Wazzzzzzzup!";
	}
}
