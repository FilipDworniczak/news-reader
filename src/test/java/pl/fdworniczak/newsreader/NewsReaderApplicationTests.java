package pl.fdworniczak.newsreader;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public abstract class NewsReaderApplicationTests {

	@Test
	public void contextLoads() {
	}

	/**
	 * Reads test resource file by path provided as parameter and returns its string content.
	 *
	 * @param path
	 * @return resource string value
	 */
	protected String getResourceStringValue(String path) {
		try {
			return IOUtils.toString(NewsReaderApplicationTests.class.getResourceAsStream(path));
		} catch (IOException e) {
			return "{}";
		}
	}

}

