package jayslabs.microservicedemo.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import jayslabs.microservicedemo.accounts.dto.CustomerDetailsDTO;
import jayslabs.microservicedemo.accounts.dto.ErrorResponseDTO;
import jayslabs.microservicedemo.accounts.service.ICustomerService;

@Tag(
		name="REST APIs for Customer in JayslabS",
		description="FETCH Customer details"
)
@RestController
@RequestMapping(path="/api", produces= {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {
	
	private final ICustomerService custsrvc;

	public CustomerController(ICustomerService custsrvc) {
		this.custsrvc=custsrvc;
	}
	
	
    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer Details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )		
	@GetMapping("/fetchCustomerDetails")
	public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
			String mobile){
    	
    	CustomerDetailsDTO dto = custsrvc.fetchCustomerDetails(mobile);
    	
    	return ResponseEntity.status(HttpStatus.OK)
    			.body(dto);
		
	}
}
