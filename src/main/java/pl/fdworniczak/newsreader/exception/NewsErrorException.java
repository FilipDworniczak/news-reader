package pl.fdworniczak.newsreader.exception;

/**
 * Created by filip on 03.01.19
 */
public class NewsErrorException extends RuntimeException {

    public NewsErrorException(String message) {
        super(message);
    }
}
