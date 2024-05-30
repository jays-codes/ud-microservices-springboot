package jayslabs.microservicedemo.loans.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jayslabs.microservicedemo.loans.constants.LoansConstants;
import jayslabs.microservicedemo.loans.dto.ErrorResponseDTO;
import jayslabs.microservicedemo.loans.dto.LoansContactInfoDTO;
import jayslabs.microservicedemo.loans.dto.LoansDTO;
import jayslabs.microservicedemo.loans.dto.ResponseDTO;
import jayslabs.microservicedemo.loans.service.ILoansService;

@Tag(
		name="CRUD REST APIs for Loans in Jayslabs",
		description="CREATE, UPDATE, FETCH, DELETE Loans details"
)
@RestController
@RequestMapping(path="/api", produces= {MediaType.APPLICATION_JSON_VALUE})
//@AllArgsConstructor
@Validated
public class LoansController {

	private static final Logger logger = LoggerFactory.getLogger(LoansController.class);	
	
	private ILoansService srvc;
	
	@Value("${build.version}")
	private String buildVersion;
	
	@Autowired
	private Environment env;

	@Autowired
	private LoansContactInfoDTO infodto;
	
	public LoansController(ILoansService srvc) {
		this.srvc=srvc;
	}
	
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

		srvc.createLoan(mobile);
		
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
	public ResponseEntity<LoansDTO> fetchLoanDetails(
			@RequestHeader("jayslabs-correlation-id") String corrId, 			
			@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
			String mobile){
		
    	logger.debug("jayslabs-correlation-id found: {}",corrId);
		
		LoansDTO custdto = srvc.fetchLoan(mobile);
		
		return ResponseEntity
				.status(HttpStatus.OK)
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
		boolean isUpdated = srvc.updateLoan(dto);
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
	
	@Operation(
			summary="Delete Loan REST API",
			description="Deletes Loan record given Phone number",
			responses= {
					@ApiResponse(responseCode="200", description="HTTP Status Loan Deleted"),
					@ApiResponse(responseCode="417", description="Excpectation Failed"),					
					@ApiResponse(responseCode="500", description="HTTP Status Internal Server Error")
			}
	)
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
			String mobile){
		boolean isDeleted = srvc.deleteLoan(mobile);
		ResponseDTO respdto = null;
		
		if (isDeleted) {
			respdto = new ResponseDTO(
					LoansConstants.STATUS_200,
					LoansConstants.MESSAGE_200);
					
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(respdto);			
		}

		respdto = new ResponseDTO(
				LoansConstants.STATUS_417,
				LoansConstants.MESSAGE_417_DELETE);

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(respdto);			
	}
	
    @Operation(
            summary = "Fetch build-info REST API",
            description = "REST API to fetch build info"
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
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildInfo(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(buildVersion);
	}
    
    @Operation(
            summary = "Fetch java version REST API",
            description = "REST API to fetch Java version"
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
	@GetMapping("/java-version")
	public ResponseEntity<String> getJdkVersion(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(env.getProperty("JAVA_HOME"));
	}
	
    @Operation(
            summary = "Fetch Support Info REST API",
            description = "REST API to fetch Support"
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
	@GetMapping("/supportinfo")
	public ResponseEntity<LoansContactInfoDTO> getContactInfo(){
    	logger.debug("Invoked Loans contact-info API");
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(infodto);
	}
}
