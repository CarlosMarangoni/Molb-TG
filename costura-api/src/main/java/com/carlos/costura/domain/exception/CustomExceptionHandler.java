package com.carlos.costura.domain.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<Object> handleNoSuchElementException(PageNotFoundException ex, WebRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(OffsetDateTime.now());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<Object> handleFileException(FileException ex, WebRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(OffsetDateTime.now());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String message = "";
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            message += nome + " " + messageSource.getMessage(error, LocaleContextHolder.getLocale()) + System.lineSeparator();
        }

        Error error = new Error();
        error.setStatus(status.value());
        error.setTimestamp(OffsetDateTime.now());
        error.setMessage(message);
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setError(ex.getMessage());

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<Object> handleAmazonServiceException(AmazonServiceException ex, WebRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(OffsetDateTime.now());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(ex.getStatusCode());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.valueOf(ex.getStatusCode()), request);

    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<Object> handleAmazonClientException(AmazonClientException ex, WebRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(OffsetDateTime.now());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<Object> handleAmazonS3Exception(AmazonS3Exception ex, WebRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(OffsetDateTime.now());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Object> authorization(AuthorizationException ex, WebRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(OffsetDateTime.now());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.FORBIDDEN.value());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException ex, WebRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(OffsetDateTime.now());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.CONFLICT.value());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
