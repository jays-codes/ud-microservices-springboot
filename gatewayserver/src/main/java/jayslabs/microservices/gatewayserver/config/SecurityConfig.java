package jayslabs.microservices.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSec) {
		httpSec
			.authorizeExchange(
				exchg -> exchg
				.pathMatchers(HttpMethod.GET)
				.permitAll()
//				.pathMatchers("/jayslabs/accounts/**").hasRole("ACCOUNTS")
//				.pathMatchers("/jayslabs/loans/**").hasRole("LOANS")
//				.pathMatchers("/jayslabs/cards/**").hasRole("CARDS"))
				.pathMatchers("/jayslabs/accounts/**").authenticated()
				.pathMatchers("/jayslabs/loans/**").authenticated()
				.pathMatchers("/jayslabs/cards/**").authenticated())

//			.oauth2ResourceServer(
//					serverspec -> serverspec
//						.jwt(jspec -> jspec.jwtAuthenticationConverter(grantedAuthoritiesExtractor()))
//		);
			
			.oauth2ResourceServer(
					serverspec -> serverspec
						.jwt(Customizer.withDefaults()));

		//no browser involved so disable
		httpSec.csrf(csrfspec -> csrfspec.disable());
		return httpSec.build();
	}
	
//	private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
//		JwtAuthenticationConverter authconverter = new JwtAuthenticationConverter();
//		authconverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleConverter());
//		return new ReactiveJwtAuthenticationConverterAdapter(authconverter);
//	}
}
