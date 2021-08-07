package jason.app.crawler.app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DownloadRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("seda:download")
		.setHeader("type", simple("${body.type}"))
		.choice()
		.when(header("type").isEqualTo("selenium"))
		  .log("selenium")
		 .when(header("type").isEqualTo("curl"))
		 .log("curl")
		 .otherwise()
		 .log("httpclient")
		.end();
	}

}
