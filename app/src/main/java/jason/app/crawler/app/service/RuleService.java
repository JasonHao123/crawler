package jason.app.crawler.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jason.app.crawler.app.model.Rule;

@Service
public class RuleService {

	public Rule getRule(String platform, String rule) {
		return new Rule();
	}

	public List<Rule> getRules(String platform) {
		List<Rule> rules = new ArrayList<>();
		rules.add(new Rule());
		return rules;
	}

}
