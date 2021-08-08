package jason.app.crawler.app.routes;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProcessRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("seda:process")
		.id("process")
		.multicast(new AggregationStrategy() {

			@Override
			public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
				List list = new ArrayList<>();
		        if (oldExchange != null) {
		            Object body = oldExchange.getIn().getBody();
		            if( body instanceof List) {
		            	list.addAll((List) body);
		            }else {
		            	list.add(body);
		            }
		        }

		        if (newExchange != null) {
		            Object body = newExchange.getIn().getBody();
		            if( body instanceof List) {
		            	list.addAll((List) body);
		            }else {
		            	list.add(body);
		            }
		        }
		        
		        if(oldExchange!=null) {
		        	oldExchange.getIn().setBody(list);
		        	return oldExchange;
		        }else {
		        	newExchange.getIn().setBody(list);
		        	return newExchange;
		        }
			}

			
		},true)
		.to("bean:process?method=callback")
		.to("bean:process?method=applyRules")
		.end()
		.split(bodyAs(List.class))
		.log("${body}");
	}

}
