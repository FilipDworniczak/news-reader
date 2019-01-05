package pl.fdworniczak.newsreader.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import pl.fdworniczak.newsreader.config.ApplicationConstants;
import pl.fdworniczak.newsreader.dto.ArticleDto;
import pl.fdworniczak.newsreader.dto.NewsDto;
import pl.fdworniczak.newsreader.exception.NewsServiceConnectionErrorException;
import pl.fdworniczak.newsreader.exception.NewsErrorException;
import pl.fdworniczak.newsreader.service.NewsExternalService;
import pl.fdworniczak.newsreader.service.util.NewsUtils;

import java.io.IOException;

/**
 * Created by filip on 03.01.19
 */

@Slf4j
@Service
public class NewsExternalServiceImpl implements NewsExternalService {

    @Autowired
    private Environment env;

    @Value("${services.newsapi.url}")
    private String newsApiUrl;

    @Override
    @Cacheable("news")
    public NewsDto getNews(final String country, final String category) {
        if (StringUtils.isEmpty(country) || StringUtils.isEmpty(category)) {
            throw new IllegalArgumentException("country or category property is empty");
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response;
        try {
            response = restTemplate.getForEntity(newsApiUrl + ApplicationConstants.NEWS_API_TOP_HEADLINES
                    + "?country=" + country + "&category=" + category + "&apiKey=" + env.getProperty("services.newsapi.key"), JsonNode.class);
        } catch (HttpStatusCodeException ex) {
            try {
                JsonNode responseBody = new ObjectMapper().readTree(ex.getResponseBodyAsString());
                throw new NewsServiceConnectionErrorException(ex.getStatusCode().value(), responseBody != null ? NewsUtils.getTextProperty(responseBody, "message") : "Connection with News api service failed.");
            } catch (IOException e) {
                throw new NewsErrorException("Connection with News api service failed.");
            }
        }

        return convertResponseToNewsDto(response.getBody(), country, category);
    }

    private NewsDto convertResponseToNewsDto(final JsonNode responseBody, final String country, final String category) throws NewsErrorException {
        if (responseBody == null) {
            throw new NewsErrorException("News response conversion failed.");
        }

        JsonNode articles = responseBody.get("articles");
        if (!articles.isArray()) {
            throw new NewsErrorException("News response conversion failed.");
        }

        NewsDto dto = new NewsDto() //
                .setCountry(country) //
                .setCategory(category);

        for (final JsonNode article : articles) {
            String author = NewsUtils.getTextProperty(article, "author");
            String title = NewsUtils.getTextProperty(article, "title");
            String description = NewsUtils.getTextProperty(article, "description");
            String articleUrl = NewsUtils.getTextProperty(article, "url");
            String imageUrl = NewsUtils.getTextProperty(article, "urlToImage");

            String date = "";
            try {
                date = DateTime.parse(NewsUtils.getTextProperty(article, "publishedAt")).toString("yyyy-MM-dd");
            } catch (IllegalArgumentException ex) {
                log.warn("PublishedAt property date parsing failed: " + NewsUtils.getTextProperty(article, "publishedAt"));
            }

            JsonNode source = article.get("source");
            String sourceName = "";
            if (source != null && source.isContainerNode()) {
                sourceName = NewsUtils.getTextProperty(source, "name");
            }

            ArticleDto articleDto = new ArticleDto(author, title, description, date, sourceName, articleUrl, imageUrl);
            dto.addArticle(articleDto);
        }

        return dto;
    }
}
