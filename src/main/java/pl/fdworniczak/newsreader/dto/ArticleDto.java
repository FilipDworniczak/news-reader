package pl.fdworniczak.newsreader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by filip on 03.01.19
 */

@AllArgsConstructor
@Getter
@Setter
public class ArticleDto {

    private String author;
    private String title;
    private String description;
    private String date;
    private String sourceName;
    private String articleUrl;
    private String imageUrl;
}
