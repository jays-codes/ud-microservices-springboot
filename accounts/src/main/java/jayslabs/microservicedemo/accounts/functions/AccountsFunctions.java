package jayslabs.microservicedemo.accounts.functions;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jayslabs.microservicedemo.accounts.service.IAccountsService;


@Configuration
public class AccountsFunctions {
	private static final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);
	
	@Bean
	public Consumer<Long> updateCommunication(IAccountsService srvc){
		
		return acctnum -> {
			log.info("Updating comm status for acct number: " + acctnum.toString());
			srvc.updateCommStatus(acctnum);
		};		
	}
	
}
