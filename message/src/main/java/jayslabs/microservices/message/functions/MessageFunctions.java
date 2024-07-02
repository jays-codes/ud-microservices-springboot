package jayslabs.microservices.message.functions;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jayslabs.microservices.message.dto.AccountsMsgDTO;

@Configuration
public class MessageFunctions {

	private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);
	
	@Bean
	public Function<AccountsMsgDTO, AccountsMsgDTO> email(){
		return  acctsmsgdto -> {
			log.info("Sending email with the details: " + acctsmsgdto.toString());
			return acctsmsgdto;
		};
	}
	
	@Bean
	public Function<AccountsMsgDTO, Long> sms(){
		return  acctsmsgdto -> {
			log.info("Sending sms with the details: " + acctsmsgdto.toString());
			return acctsmsgdto.accountNumber();
		};
	}
}
