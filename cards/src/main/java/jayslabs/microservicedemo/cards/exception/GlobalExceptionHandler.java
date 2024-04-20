package jayslabs.microservicedemo.cards.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jayslabs.microservicedemo.cards.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleGlobalException(
			Exception exception, WebRequest webRequest){
		
		ErrorResponseDTO errrespdto = new ErrorResponseDTO(
		webRequest.getDescription(false),
		HttpStatus.INTERNAL_SERVER_ERROR,
		exception.getMessage(),
		LocalDateTime.now());
		
		return new ResponseEntity<>(errrespdto, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatusCode status, 
			WebRequest request) {
		
		Map<String, String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

		validationErrorList.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String validationMsg = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMsg);
		});
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CardsAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(
			CardsAlreadyExistsException exception, WebRequest webRequest){
		
		ErrorResponseDTO errrespdto = new ErrorResponseDTO(
		webRequest.getDescription(false),
		HttpStatus.BAD_REQUEST,
		exception.getMessage(),
		LocalDateTime.now());
		
		return new ResponseEntity<>(errrespdto, HttpStatus.BAD_REQUEST);
	}
	
	
//	@ExceptionHandler(LoanDoesNotExistException.class)
//	public ResponseEntity<ErrorResponseDTO> handleCustomerDoesNotExistException(
//			LoanDoesNotExistException exception, WebRequest webRequest){
//		
//		ErrorResponseDTO errrespdto = new ErrorResponseDTO(
//		webRequest.getDescription(false),
//		HttpStatus.NOT_FOUND,
//		exception.getMessage(),
//		LocalDateTime.now());
//		
//		return new ResponseEntity<>(errrespdto, HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
			ResourceNotFoundException exception, WebRequest webRequest){
		
		ErrorResponseDTO errrespdto = new ErrorResponseDTO(
		webRequest.getDescription(false),
		HttpStatus.NOT_FOUND,
		exception.getMessage(),
		LocalDateTime.now());
		
		return new ResponseEntity<>(errrespdto, HttpStatus.NOT_FOUND);
	}
}
