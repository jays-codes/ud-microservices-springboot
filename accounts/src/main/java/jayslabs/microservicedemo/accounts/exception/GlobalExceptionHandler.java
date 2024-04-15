package jayslabs.microservicedemo.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jayslabs.microservicedemo.accounts.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(
			CustomerAlreadyExistsException exception, WebRequest webRequest){
		
		ErrorResponseDTO errrespdto = new ErrorResponseDTO(
		webRequest.getDescription(false),
		HttpStatus.BAD_REQUEST,
		exception.getMessage(),
		LocalDateTime.now());
		
		return new ResponseEntity<>(errrespdto, HttpStatus.BAD_REQUEST);
	}
}
