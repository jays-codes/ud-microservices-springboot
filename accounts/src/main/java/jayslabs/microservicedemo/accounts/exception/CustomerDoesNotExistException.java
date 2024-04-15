package jayslabs.microservicedemo.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class CustomerDoesNotExistException extends RuntimeException {
	
	public CustomerDoesNotExistException(String msg) {
		super(msg);
	}
	
	
}
