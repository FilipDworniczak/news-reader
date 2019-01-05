package pl.fdworniczak.newsreader.rest;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fdworniczak.newsreader.config.ApplicationConstants;
import pl.fdworniczak.newsreader.dto.NewsDto;
import pl.fdworniczak.newsreader.service.NewsService;

import javax.inject.Inject;

/**
 * News REST controller.
 *
 * Created by filip on 03.01.19
 */

@RestController
@RequestMapping(value = ApplicationConstants.REST_PREFIX + ApplicationConstants.NEWS)
@Api("News controller. Serves worldwide news data.")
public class NewsController {

    @Inject
    private NewsService newsService;

    /**
     * Get news filtered by country and category.
     *
     * @param country
     *      param country - The 2-letter ISO 3166-1 code of the country for which news will be returned
     *
     * @param category
     *      News category
     *
     * @return String
     */
    @GetMapping("/{country}/{category}")
    public ResponseEntity<NewsDto> findByCountryAndCategory(@PathVariable String country, @PathVariable String category) {
        return new ResponseEntity<>(newsService.getNews(country, category), HttpStatus.OK);
    }

}