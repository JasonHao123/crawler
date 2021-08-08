package jason.app.crawler.app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DownloadRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("seda:download").id("download")
		.setHeader("type", simple("${body.type}"))
		.setHeader("request",body())
		.choice()
		.when(header("type").isEqualTo("selenium"))
		  .to("bean:downloader?method=selenium")
		 .when(header("type").isEqualTo("curl"))
		 .to("bean:downloader?method=curl")
		 .otherwise()
		 .to("bean:downloader?method=httpclient")
		.end()
		.to("seda:process");
	}

}
