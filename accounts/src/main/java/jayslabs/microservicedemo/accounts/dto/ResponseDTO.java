package jayslabs.microservicedemo.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(
		name="Response",
		description="Holds Response information"
)
public class ResponseDTO {
	
	private String statusCode;
	private String statusMsg;
}
