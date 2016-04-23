package wavsep.service.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wavsep.service.model.Response;

@RestController
public class OsCommandController {
	
	private String etcpasswd = "root:x:0:0:root:/root:/bin/bash\n"
			+ "daemon:x:1:1:daemon:/usr/sbin:/bin/sh\n"
			+ "bin:x:2:2:bin:/bin:/bin/sh\n"
			+ "sys:x:3:3:sys:/dev:/bin/sh\n"
			+ "sync:x:4:65534:sync:/bin:/bin/sync\n"
			+ "games:x:5:60:games:/usr/games:/bin/sh\n"
			+ "man:x:6:12:man:/var/cache/man:/bin/sh\n"
			+ "lp:x:7:7:lp:/var/spool/lpd:/bin/sh\n"
			+ "mail:x:8:8:mail:/var/mail:/bin/sh\n"
			+ "news:x:9:9:news:/var/spool/news:/bin/sh\n"
			+ "uucp:x:10:10:uucp:/var/spool/uucp:/bin/sh\n"
			+ "proxy:x:13:13:proxy:/bin:/bin/sh\n"
			+ "www-data:x:33:33:www-data:/var/www:/bin/sh\n";
	
	//OS Command mock up

    @RequestMapping(value="/mock/oscommand", method = RequestMethod.POST, consumes={"application/json","application/xml"} )
    public Response oscommand(@RequestBody String input){
    	
    	Response resp = new Response();
    	
    	if(input.contains("\"/bin/cat /etc/passwd")){
    		resp.setResponseBody(etcpasswd);
    	}
    	
        return resp;
    }
    
}
