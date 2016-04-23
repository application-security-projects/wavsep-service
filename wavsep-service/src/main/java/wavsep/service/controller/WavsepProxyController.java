package wavsep.service.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wavsep.service.model.Request;
import wavsep.service.model.Response;

@RestController
public class WavsepProxyController {
	
	private static String wavsephost = "http://wavsep.test:8080/";
	private static String wavsepapp = "wavsep/";
	private static String extention = ".jsp";

    @RequestMapping(value="/proxy/{type}/{attack}/{suite}/{testcase}", method = RequestMethod.POST, consumes={"application/json","application/xml"} )
    public Response proxy(@RequestBody Request req, @PathVariable String type, @PathVariable String attack, 
    		@PathVariable String suite, 
    		@PathVariable String testcase) throws Exception {
    	
    	String path = wavsepapp + type + "/" + attack + "/" + suite + "/" + testcase + extention;
    	
    	Response resp = new Response();
    	resp = doPost(req, path);
    	
        return resp;
    }
    
    private static Response doPost(Request req, String path) throws Exception {
    	
    	Response resp = new Response();
    	String respB = "";
    	path = wavsephost + path;
        URL url = new URL(path);
        Map<String,Object> params = req.getBody();

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        for (int c; (c = in.read()) >= 0; respB += (char)c, System.out.print((char)c));
        
        //TODO: Filter CLRF?
        
        resp.setResponseBody(respB);
        
		return resp;
    }
}
