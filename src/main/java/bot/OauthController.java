package bot;

import java.util.Properties;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableAutoConfiguration
public class OauthController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Properties properties;

    @RequestMapping("/")
    public @ResponseBody String home() {
        return "Hello World!";
    }
    
    @RequestMapping("/access_token")
    public @ResponseBody String tokenResponse() {	
    	String username = properties.getProperty("username");
    	String password = properties.getProperty("password");
    	String json = "grant_type=password&username="+username+"&password="+password;
    	HttpHeaders headers = new HttpHeaders();
        String auth = "FSQcWWRo7faO5w" + ":" + "LCAYvX6RrEWaU7VwdFD1ghOk1H0";
        byte[] plainCredsBytes = auth.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	headers.set("Authorization", "Basic "+base64Creds);
    	headers.set("User-agent", "SomeStupidAppBlah");
    	HttpEntity<String> entity = new HttpEntity<String>(json, headers);
    	Object result = restTemplate.exchange("https://www.reddit.com/api/v1/access_token", HttpMethod.POST, entity, Object.class);
    	return ReflectionToStringBuilder.toString(result);
    }
    

}