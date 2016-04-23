package wavsep.service.model;

import java.util.Map;

public class Request {
	
	private Map<String,Object> body;
	
	public Map<String, Object> getBody() {
		return body;
	}
	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

}
