package jayslabs.microservicedemo.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jayslabs.microservicedemo.accounts.dto.AccountsContactInfoDTO;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
@EnableConfigurationProperties(value= {AccountsContactInfoDTO.class})
@OpenAPIDefinition(
		info=@Info(
				title="DEMO - Accounts microservice REST API",
				description="Jayslabs Accounts microservice REST API Documentation",
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
				description="Jayslabs Accounts microservice REST API Documentation",
				url="http://localhost:8082/swagger-ui/index.html#/"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
