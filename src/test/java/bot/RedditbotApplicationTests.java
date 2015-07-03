package bot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {OauthController.class, SimpleConfig.class})
public class RedditbotApplicationTests {
	
	@Autowired
	OauthController controller;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testAccessToken() {
		System.out.println(controller.tokenResponse());
	}

}
