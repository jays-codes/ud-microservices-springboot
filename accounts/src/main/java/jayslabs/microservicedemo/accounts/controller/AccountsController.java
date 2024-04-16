package jayslabs.microservicedemo.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jayslabs.microservicedemo.accounts.constants.AccountsConstants;
import jayslabs.microservicedemo.accounts.dto.CustomerDTO;
import jayslabs.microservicedemo.accounts.dto.ErrorResponseDTO;
import jayslabs.microservicedemo.accounts.dto.ResponseDTO;
import jayslabs.microservicedemo.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;

@Tag(
		name="CRUD REST APIs for Accounts in JayslabS",
		description="CREATE, UPDATE, FETCH, DELETE Account details"
)
@RestController
@RequestMapping(path="/api", produces= {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {
	
	private IAccountsService acctsrvc;
	
	@Operation(
			summary="Create Account REST API",
			description="Creates a new Customer and Account. Customers must have unique phone numbers.",
			responses= {
					@ApiResponse(responseCode="201", description="HTTP Status CREATED")					
			}
	)
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO custdto){
		
		acctsrvc.createAccount(custdto);
		
		ResponseDTO respdto = new ResponseDTO(
				AccountsConstants.STATUS_201,
				AccountsConstants.MESSAGE_201);
				
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(respdto);
	}
	
	@Operation(
			summary="Fetch Account REST API",
			description="Retrieves Account Details using Phone Number.",
			responses= {
					@ApiResponse(responseCode="200", description="HTTP Status OK")					
			}
	)
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
			String mobile){
		CustomerDTO custdto = acctsrvc.fetchAccount(mobile);
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(custdto);
	}
	
	@Operation(
			summary="Update Account REST API",
			description="Updates Account Details. Acct No. specified in Request Body must be existing.",
			responses = {
					@ApiResponse(responseCode="201", description="HTTP Status UPDATED"),
					@ApiResponse(responseCode="500", description="HTTP Status Internal Server Error",
						content=@Content(
								schema=@Schema(implementation=ErrorResponseDTO.class)
						)
					)
			}
	)
	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO custdto){
		boolean isUpdated = acctsrvc.updateAccount(custdto);
		ResponseDTO respdto = null;
		
		if (isUpdated) {
			respdto = new ResponseDTO(
					AccountsConstants.STATUS_201,
					AccountsConstants.MESSAGE_201);
					
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(respdto);			
		}

		respdto = new ResponseDTO(
				AccountsConstants.STATUS_500,
				AccountsConstants.MESSAGE_500);

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(respdto);			
	}
	
	@Operation(
			summary="Delete Account REST API",
			description="Deletes Account using Phone number. Deletes both Account and Customer record.",
			responses= {
					@ApiResponse(responseCode="200", description="HTTP Status Account Deleted"),
					@ApiResponse(responseCode="500", description="HTTP Status Internal Server Error")
			}
	)
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
			String mobile){
		boolean isDeleted = acctsrvc.deleteAccount(mobile);
		ResponseDTO respdto = null;
		
		if (isDeleted) {
			respdto = new ResponseDTO(
					AccountsConstants.STATUS_200,
					AccountsConstants.MESSAGE_200);
					
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(respdto);			
		}

		respdto = new ResponseDTO(
				AccountsConstants.STATUS_417,
				AccountsConstants.MESSAGE_417_DELETE);

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(respdto);			
	}
}
