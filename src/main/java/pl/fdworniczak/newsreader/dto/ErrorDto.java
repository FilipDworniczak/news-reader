package pl.fdworniczak.newsreader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Error wrapper object for ExceptionHandlingController
 *
 * Created by filip on 05.01.19
 */

@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

    private int statusCode;
    private String message;
}
