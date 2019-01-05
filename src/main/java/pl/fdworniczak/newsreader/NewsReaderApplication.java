package pl.fdworniczak.newsreader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class NewsReaderApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(NewsReaderApplication.class, args);
	}

	@PostConstruct
	public void applicationStarted() {
		if (StringUtils.isEmpty(env.getProperty("services.newsapi.key"))) {
			log.warn("services.newsapi.key property is not provided. Please provide it with run param: -Dservices.newsapi.key=${NEWS_API_KEY}");
		}
	}

}

