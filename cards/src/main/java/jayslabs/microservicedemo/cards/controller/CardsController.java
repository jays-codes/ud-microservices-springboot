package jayslabs.microservicedemo.cards.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jayslabs.microservicedemo.cards.constants.CardsConstants;
import jayslabs.microservicedemo.cards.dto.CardsContactInfoDTO;
import jayslabs.microservicedemo.cards.dto.CardsDTO;
import jayslabs.microservicedemo.cards.dto.ErrorResponseDTO;
import jayslabs.microservicedemo.cards.dto.ResponseDTO;
import jayslabs.microservicedemo.cards.service.ICardsService;

@RestController
@RequestMapping(path="/api", produces= {MediaType.APPLICATION_JSON_VALUE})
//@AllArgsConstructor
@Validated
public class CardsController {

	private final ICardsService srvc;

	@Value("${build.version}")
	private String buildVersion;
	
	@Autowired
	private Environment env;

	@Autowired
	private CardsContactInfoDTO infodto;	
	
	public CardsController(ICardsService srvc) {
		this.srvc=srvc;
	}
	
	@Operation(
			summary="Create Card REST API",
			description="Creates a new Card. Unique phone numbers should be provided.",
			responses= {
					@ApiResponse(responseCode="201", description="HTTP Status CREATED")					
			}
	)
	@PostMapping("/create")	
	public ResponseEntity<ResponseDTO> createCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
	String mobile){
		srvc.createCard(mobile);
		
		ResponseDTO dto = new ResponseDTO(CardsConstants.STATUS_201,
				CardsConstants.MESSAGE_201);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(dto);
	}
	
	@Operation(
			summary="Fetch Cards REST API",
			description="Retrieves Card Details using Phone Number.",
			responses= {
					@ApiResponse(responseCode="200", description="HTTP Status OK")					
			}
	)
	@GetMapping("/fetch")
	public ResponseEntity<CardsDTO> fetchCardDetails(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
			String mobile){
		CardsDTO custdto = srvc.fetchCard(mobile);
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(custdto);
	}
	
	@Operation(
			summary="Update Cards REST API",
			description="Updates Card Details. Card No. specified in Request Body must be existing.",
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
	public ResponseEntity<ResponseDTO> updateCardDetails(@Valid @RequestBody CardsDTO dto){
		boolean isUpdated = srvc.updateCard(dto);
		ResponseDTO respdto = null;
		
		if (isUpdated) {
			respdto = new ResponseDTO(
					CardsConstants.STATUS_200,
					CardsConstants.MESSAGE_200);
					
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(respdto);			
		}

		respdto = new ResponseDTO(
				CardsConstants.STATUS_417,
				CardsConstants.MESSAGE_417_UPDATE);

		return ResponseEntity
				.status(HttpStatus.EXPECTATION_FAILED)
				.body(respdto);			
	}
	
	
	@Operation(
			summary="Delete Card REST API",
			description="Deletes Card record given Phone number",
			responses= {
					@ApiResponse(responseCode="200", description="HTTP Status Card Deleted"),
					@ApiResponse(responseCode="417", description="Excpectation Failed"),					
					@ApiResponse(responseCode="500", description="HTTP Status Internal Server Error")
			}
	)	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDTO> deleteCard(@RequestParam 
			@Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
			String mobile){
		boolean isDeleted = srvc.deleteCard(mobile);
		ResponseDTO dto = null;
		
		if (isDeleted) {
			dto = new ResponseDTO(
					CardsConstants.STATUS_200,
					CardsConstants.MESSAGE_200);
					
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(dto);			
		}

		dto = new ResponseDTO(
				CardsConstants.STATUS_417,
				CardsConstants.MESSAGE_417_DELETE);

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(dto);	
		
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
	public ResponseEntity<CardsContactInfoDTO> getContactInfo(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(infodto);
	}
	
}
