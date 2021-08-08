package jason.app.crawler.app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProcessRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("seda:process").id("process").log("process");
	}

}
