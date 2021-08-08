package jason.app.crawler.app.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import jason.app.crawler.app.model.Request;

@Component
public class StartUrlRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:start_url?period=1s").id("start-url-route")
				.setHeader("CamelRedis.Key", constant("explore:start_urls"))
				.to("spring-redis:localhost:6379?serializer=#stringSerializer&command=RPOP")
				.choice()
				    .when(body().isNotNull()).unmarshal(new JacksonDataFormat(Request.class))
				    .to("bean:request?method=force")
					.process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
					        Request payload = exchange.getIn().getBody(Request.class);
					        payload.setScore(0D);
					        payload.setFollow(true);
					        exchange.getIn().setBody(payload);	
						}
					})
					.to("seda:schedule")
				.end();
	}

}
