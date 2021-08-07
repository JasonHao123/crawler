package jason.app.crawler.app.service;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

import jason.app.crawler.app.model.Request;

@Service("request")
public class RequestService {

	public void force(Exchange exchange) {
        Request payload = exchange.getIn().getBody(Request.class);
        payload.setForce(true);
        exchange.getIn().setBody(payload);
	}
}
