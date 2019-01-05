package pl.fdworniczak.newsreader.exception;

import lombok.Getter;

/**
 * Created by filip on 04.01.19
 */

@Getter
public class NewsServiceConnectionErrorException extends RuntimeException {

    private final int statusCode;
    private final String message;

    public NewsServiceConnectionErrorException(int statusCode, String message) {
        super("");
        this.statusCode = statusCode;
        this.message = message;
    }
}
