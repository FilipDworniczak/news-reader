package pl.fdworniczak.newsreader.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filip on 03.01.19
 */

@Getter
@Setter
public class NewsDto {

    private String country;
    private String category;
    private List<ArticleDto> articles = new ArrayList<>();

    public NewsDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public NewsDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public void addArticle(final ArticleDto articleDto) {
        this.articles.add(articleDto);
    }
}
