package jayslabs.microservicedemo.loans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import jayslabs.microservicedemo.loans.constants.LoansConstants;
import jayslabs.microservicedemo.loans.dto.ErrorResponseDTO;
import jayslabs.microservicedemo.loans.dto.LoansDTO;
import jayslabs.microservicedemo.loans.dto.ResponseDTO;
import jayslabs.microservicedemo.loans.service.ILoansService;
import lombok.AllArgsConstructor;

@Tag(
		name="CRUD REST APIs for Loans in Jayslabs",
		description="CREATE, UPDATE, FETCH, DELETE Loans details"
)
@RestController
@RequestMapping(path="/api", produces= {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

	private ILoansService loansrvc;
	
	@Operation(
			summary="Create Loan REST API",
			description="Creates a new Loan. Unique phone numbers should be provided.",
			responses= {
					@ApiResponse(responseCode="201", description="HTTP Status CREATED")					
			}
	)
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createLoan(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
			String mobile){

		loansrvc.createLoan(mobile);
		
		ResponseDTO respdto = new ResponseDTO(
				LoansConstants.STATUS_201,
				LoansConstants.MESSAGE_201);
				
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(respdto);
	}
	
	@Operation(
			summary="Fetch Loan REST API",
			description="Retrieves Loan Details using Phone Number.",
			responses= {
					@ApiResponse(responseCode="200", description="HTTP Status OK")					
			}
	)
	@GetMapping("/fetch")
	public ResponseEntity<LoansDTO> fetchAccountDetails(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
			String mobile){
		LoansDTO custdto = loansrvc.fetchLoan(mobile);
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(custdto);
	}
	
	@Operation(
			summary="Update Loans REST API",
			description="Updates Loan Details. Loan No. specified in Request Body must be existing.",
			responses = {
					@ApiResponse(responseCode="200", description="HTTP Status UPDATED"),
					@ApiResponse(responseCode="500", description="HTTP Status Internal Server Error",
						content=@Content(
								schema=@Schema(implementation=ErrorResponseDTO.class)
						)
					),
					@ApiResponse(responseCode="417", description="Expectation Failed")					
			}
	)
	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoansDTO dto){
		boolean isUpdated = loansrvc.updateLoan(dto);
		ResponseDTO respdto = null;
		
		if (isUpdated) {
			respdto = new ResponseDTO(
					LoansConstants.STATUS_200,
					LoansConstants.MESSAGE_200);
					
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(respdto);			
		}

		respdto = new ResponseDTO(
				LoansConstants.STATUS_417,
				LoansConstants.MESSAGE_417_UPDATE);

		return ResponseEntity
				.status(HttpStatus.EXPECTATION_FAILED)
				.body(respdto);			
	}
}
