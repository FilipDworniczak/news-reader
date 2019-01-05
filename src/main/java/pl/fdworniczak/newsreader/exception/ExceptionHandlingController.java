package pl.fdworniczak.newsreader.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.fdworniczak.newsreader.dto.ErrorDto;

/**
 * Exception handling controller. It catches build-in java exception thrown in rest services and provides proper response status.
 *
 * Created by filip on 03.01.19
 */

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NewsServiceConnectionErrorException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleNewsServiceConnectionErrorException(NewsServiceConnectionErrorException ex) {
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getStatusCode());
        return new ResponseEntity<>(new ErrorDto(ex.getStatusCode(), ex.getMessage()), httpStatus);
    }

    @ExceptionHandler(NewsErrorException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleNewsServiceConnectionFailedException(NewsErrorException ex) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
