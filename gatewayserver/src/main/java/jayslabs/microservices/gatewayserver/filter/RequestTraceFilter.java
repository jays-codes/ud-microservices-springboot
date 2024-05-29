package jayslabs.microservices.gatewayserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter{
	private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);
	
	@Autowired
	FilterUtility filterUtil;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchg, GatewayFilterChain chain){
		HttpHeaders reqHeaders = exchg.getRequest().getHeaders();
		if (isCorrelationIdPresent(reqHeaders)) {
			logger.debug("jayslabs-correlation-id found in RequestTraceFilter: {}", filterUtil.getCorrelationId(reqHeaders));
		} else {
			String corrId = generateCorrelationId();
			exchg = filterUtil.setCorrelationId(exchg, corrId);
		}
		return chain.filter(exchg);
	}

	private boolean isCorrelationIdPresent(HttpHeaders reqHeaders) {
		if (filterUtil.getCorrelationId(reqHeaders)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}
}
