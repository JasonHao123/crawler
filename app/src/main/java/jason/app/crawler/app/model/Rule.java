package jason.app.crawler.app.model;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

public class Rule {
	private ResultTemplate template;
	private boolean follow;
	private String type;
	private Long expireTime;
	private String pageAction;
	private List<String> tags;
	private List<String> restrictedCss;
	private List<String> allow;
	private List<String> deny;
	private String nextPageChecker;
	private List<String> actionBeforePage;
	
	
	public List<Result> processCallback(Request request, Document response) {
		List<Result> result = new ArrayList<>();
		
		
		return result;
	}

	public List<Result> process(Request request, Document response) {
		List<Result> result = new ArrayList<>();
		Item item = new Item();
		result.add(item);
		return result;
	}

	public ResultTemplate getTemplate() {
		return template;
	}

	public void setTemplate(ResultTemplate template) {
		this.template = template;
	}

	public boolean isFollow() {
		return follow;
	}

	public void setFollow(boolean follow) {
		this.follow = follow;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public String getPageAction() {
		return pageAction;
	}

	public void setPageAction(String pageAction) {
		this.pageAction = pageAction;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getRestrictedCss() {
		return restrictedCss;
	}

	public void setRestrictedCss(List<String> restrictedCss) {
		this.restrictedCss = restrictedCss;
	}

	public List<String> getAllow() {
		return allow;
	}

	public void setAllow(List<String> allow) {
		this.allow = allow;
	}

	public List<String> getDeny() {
		return deny;
	}

	public void setDeny(List<String> deny) {
		this.deny = deny;
	}

	public String getNextPageChecker() {
		return nextPageChecker;
	}

	public void setNextPageChecker(String nextPageChecker) {
		this.nextPageChecker = nextPageChecker;
	}

	public List<String> getActionBeforePage() {
		return actionBeforePage;
	}

	public void setActionBeforePage(List<String> actionBeforePage) {
		this.actionBeforePage = actionBeforePage;
	}
	
	

}
