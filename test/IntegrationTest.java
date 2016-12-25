import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

public class IntegrationTest {

	/**
	 * add your integration test here
	 * in this example we just check if the welcome page is being shown
	 */
	@Test
	public void test() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
			browser.goTo("http://localhost:3333");
			assertTrue(browser.pageSource().contains("Wykop-bot is running!"));
		});
	}

}
