package jason.app.crawler.app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import jason.app.crawler.app.model.Request;

@Component
public class StartUrlRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:start_url?period=1s")
				.setHeader("CamelRedis.Key", constant("explore:start_urls"))
				.to("spring-redis:localhost:6379?serializer=#stringSerializer&command=RPOP")
				.choice()
				    .when(body().isNotNull()).unmarshal(new JacksonDataFormat(Request.class))
				    .to("bean:request?method=force")
				    .setHeader("score", constant(0D))
					.to("seda:schedule")
				.end();
	}

}
