package jason.app.crawler.app.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.support.processor.idempotent.MemoryIdempotentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import jason.app.crawler.app.model.Request;

@Component
public class SchedulerRoute extends RouteBuilder{
	
	@Autowired
	private RedisTemplate<String, Request> redisTemplate;

	@Override
	public void configure() throws Exception {
		from("seda:schedule").id("schedule")
		.choice().when(simple("${body.isForce()} == false")).idempotentConsumer(simple("${body.url}"),new MemoryIdempotentRepository()).to("seda:saveToQueue").endChoice().otherwise().to("seda:saveToQueue").end();
		
		from("seda:saveToQueue").id("save-to-queue")
		.setHeader("CamelRedis.Key", constant("explore:requests"))
		.setHeader("CamelRedis.Score",simple("${body.getScore()}"))
		.marshal(new JacksonDataFormat(Request.class))
		.setHeader("CamelRedis.value",body().convertToString())
		.to("spring-redis:localhost:6379?serializer=#stringSerializer&command=ZADD");
		
		from("timer:scheduler?period=1s").id("schedule-download")
//		.setHeader("CamelRedis.Key", constant("explore:requests"))
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				TypedTuple<Request> result = redisTemplate.opsForZSet().popMin("explore:requests");
				if(result!=null) {
					exchange.getIn().setBody(result.getValue());
				}
			}
		})
		.choice()
		.when(body().isNotNull()).to("seda:download")
		.end();
		
	}

}
