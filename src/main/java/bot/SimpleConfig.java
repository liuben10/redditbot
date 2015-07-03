package bot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class SimpleConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Properties properties() throws IOException {
		Properties prop = new Properties();
		InputStream stream = getClass().getClassLoader().getResourceAsStream("application.properties");
		if (stream != null) {
			prop.load(stream);
		} else {
			throw new FileNotFoundException("config.properties");
		}
		return prop;
	}

}
