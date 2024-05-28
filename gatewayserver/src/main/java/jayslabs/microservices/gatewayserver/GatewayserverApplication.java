package jayslabs.microservices.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator jayslabsRouteConfig (RouteLocatorBuilder rlb) {
		return rlb.routes()
			.route(
					p -> p
						.path("/jayslabs/accounts/**")
						.filters(f->f.rewritePath("/jayslabs/accounts/(?<segment>.*)", "/${segment}"))
						.uri("lb://ACCOUNTS"))
			.route(
					p -> p
						.path("/jayslabs/loans/**")
						.filters(f->f.rewritePath("/jayslabs/loans/(?<segment>.*)", "/${segment}"))
						.uri("lb://LOANS"))
			.route(
					p -> p
						.path("/jayslabs/cards/**")
						.filters(f->f.rewritePath("/jayslabs/cards/(?<segment>.*)", "/${segment}"))
						.uri("lb://CARDS"))
			.build();								
					
			
	}
}
