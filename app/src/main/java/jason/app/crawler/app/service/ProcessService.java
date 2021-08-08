package jason.app.crawler.app.service;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

@Service("process")
public class ProcessService {

	public void callback(Exchange exchange) {
		exchange.getIn().setBody("hello");
	}

	public void applyRules(Exchange exchange) {
		exchange.getIn().setBody("world");
	}
}
