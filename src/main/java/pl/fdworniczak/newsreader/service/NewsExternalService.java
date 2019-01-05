package pl.fdworniczak.newsreader.service;

import pl.fdworniczak.newsreader.dto.NewsDto;

/**
 * News external service.
 *
 * Created by filip on 03.01.19
 */
public interface NewsExternalService {

    /**
     * Get news from external service.
     *
     * @param country - The 2-letter ISO 3166-1 code of the country for which news will be returned
     * @param category - News category
     * @return NewsDto object
     */
    NewsDto getNews(String country, String category);
}
