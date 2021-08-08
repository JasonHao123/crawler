package jason.app.crawler.app.model;

import java.util.List;
import java.util.Map;

public class ResultTemplate {
	private boolean list;
	private String selector;
	private List<ResultTemplate> children;
	private Map<String,String[]> fields;
	public boolean isList() {
		return list;
	}
	public void setList(boolean list) {
		this.list = list;
	}
	public List<ResultTemplate> getChildren() {
		return children;
	}
	public void setChildren(List<ResultTemplate> children) {
		this.children = children;
	}
	public Map<String, String[]> getFields() {
		return fields;
	}
	public void setFields(Map<String, String[]> fields) {
		this.fields = fields;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	
	
}
