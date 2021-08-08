package jason.app.crawler.app.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jason.app.crawler.app.model.Request;
import jason.app.crawler.app.model.Result;
import jason.app.crawler.app.model.Rule;

@Service("process")
public class ProcessService {

	@Autowired
	private RuleService ruleService;

	public void callback(Exchange exchange) {
		Request request = exchange.getIn().getHeader("request", Request.class);
		Document response = exchange.getIn().getBody(Document.class);
		Rule rule = ruleService.getRule(request.getPlatform(), request.getRule());
		if (rule != null) {
			exchange.getIn().setBody(rule.processCallback(request, response));
		}
	}

	public void applyRules(Exchange exchange) {
		Request request = exchange.getIn().getHeader("request", Request.class);
		Document response = exchange.getIn().getBody(Document.class);
		List<Result> results = new ArrayList<>();
		if (request.isFollow()) {
			List<Rule> rules = ruleService.getRules(request.getPlatform());
			for (Rule rule : rules) {
				results.addAll(rule.process(request, response));
			}

		}
		exchange.getIn().setBody(results);
	}
}
