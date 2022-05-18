package co.simplon.p25.dessinemoiun.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    // Handle RestTemplateException to forward external APIs status
    // and optional body in case of error
    @ExceptionHandler(RestTemplateException.class)
    protected ResponseEntity<Object> handleRestTemplateException(
	    RestTemplateException ex, WebRequest request) {
	return handleExceptionInternal(ex, ex.getBody(), new HttpHeaders(),
		ex.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
	    Object body, HttpHeaders headers, HttpStatus status,
	    WebRequest request) {
	return super.handleExceptionInternal(ex, body, headers, status,
		request);
    }
}