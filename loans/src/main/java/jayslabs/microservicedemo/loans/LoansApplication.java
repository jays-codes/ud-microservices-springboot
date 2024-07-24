package jayslabs.microservicedemo.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jayslabs.microservicedemo.loans.dto.LoansContactInfoDTO;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
@EnableConfigurationProperties(value= {LoansContactInfoDTO.class})
@OpenAPIDefinition(
		info=@Info(
				title="DEMO - Loans microservice REST API",
				description="Jayslabs Loans microservice REST API Documentation",
				version="v1",
				contact=@Contact(
						name="Jay Menorca",
						email="jmenorca@gmail.com",
						url="https://www.jayslabs.ca"
				),
				license=@License(
						name="Apache 2.0",
						url="https://www.apache.org/licenses/LICENSE-2.0"
				)
				
				
				
		),
		externalDocs=@ExternalDocumentation(
				description="Jayslabs Loans microservice REST API Documentation",
				url="http://localhost:8092/swagger-ui/index.html#/"
		)
)

public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
