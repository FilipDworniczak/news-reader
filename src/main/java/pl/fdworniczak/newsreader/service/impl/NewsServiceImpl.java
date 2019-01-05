package pl.fdworniczak.newsreader.service.impl;

import org.springframework.stereotype.Service;
import pl.fdworniczak.newsreader.dto.NewsDto;
import pl.fdworniczak.newsreader.service.NewsExternalService;
import pl.fdworniczak.newsreader.service.NewsService;

import javax.inject.Inject;

/**
 * Created by filip on 03.01.19
 */

@Service
public class NewsServiceImpl implements NewsService {

    @Inject
    private NewsExternalService newsExternalService;

    @Override
    public NewsDto getNews(final String country, final String category) {
        return newsExternalService.getNews(country, category);
    }
}
