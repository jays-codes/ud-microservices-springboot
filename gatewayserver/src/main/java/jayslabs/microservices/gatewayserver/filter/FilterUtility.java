package jayslabs.microservices.gatewayserver.filter;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {
	public static final String CORRELATION_ID = "jayslabs-correlation-id";
	
	public String getCorrelationId(HttpHeaders reqHeaders) {
		if (reqHeaders.get(CORRELATION_ID)!=null) {
			List<String> reqHeaderList = reqHeaders.get(CORRELATION_ID);
			return reqHeaderList.stream().findFirst().get();
		} else {
			return null;
		}
		
	}
	
	public ServerWebExchange setRequestHeader(ServerWebExchange exchg, String name, String value) {
		return exchg.mutate().request(exchg.getRequest().mutate().header(name, value).build()).build();
	}
	
	public ServerWebExchange setCorrelationId(ServerWebExchange exchg, String correlationId) {
		return this.setRequestHeader(exchg, CORRELATION_ID, correlationId);
	}
}
