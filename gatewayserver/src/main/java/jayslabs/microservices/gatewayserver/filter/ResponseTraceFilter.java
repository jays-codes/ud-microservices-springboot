package jayslabs.microservices.gatewayserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {
	private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);
	
	@Autowired
	FilterUtility filterUtil;
	
	@Bean
	public GlobalFilter postGlobalFilter() {
		return(exchange, chain) -> {
			return chain.filter(exchange).then(Mono.fromRunnable(()->{
				HttpHeaders reqHeaders = exchange.getRequest().getHeaders();
				String corrId = filterUtil.getCorrelationId(reqHeaders);
				
				if (!(exchange.getResponse().getHeaders().containsKey(filterUtil.CORRELATION_ID))) {
					logger.debug("Updated the correlation id to the outbound headers: {}", corrId);
					exchange.getResponse().getHeaders().add(filterUtil.CORRELATION_ID, corrId);
					
				}
				
				
			}));
		};
	}
}
