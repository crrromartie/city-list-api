package pl.solberg.cities.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.solberg.cities.exception.ExceptionResponseDto;
import pl.solberg.cities.exception.ResourceNotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdviceController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponseDto handleResourceNotFoundException(
            ResourceNotFoundException e) {
        log.error(e.getLocalizedMessage());
        return ExceptionResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .errorMessage(e.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ExceptionResponseDto handleConstraintViolationException(
            ConstraintViolationException e) {
        var exceptionMessage = new StringBuilder();
        e.getConstraintViolations().forEach(
                constraintViolation -> exceptionMessage.append(constraintViolation.getMessage()).append(";")
        );
        log.error(exceptionMessage.toString());
        return ExceptionResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .errorMessage(exceptionMessage.toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public ExceptionResponseDto handleHibernateConstraintViolationException(
            org.hibernate.exception.ConstraintViolationException e) {
        log.error(e.getLocalizedMessage());
        return ExceptionResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .errorMessage(e.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ExceptionResponseDto handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        log.error(e.getLocalizedMessage());
        return ExceptionResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .errorMessage(e.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionResponseDto handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        log.error(e.getLocalizedMessage());
        return ExceptionResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .errorMessage(e.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionResponseDto handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        log.error(e.getLocalizedMessage());
        return ExceptionResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .errorMessage(e.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ExceptionResponseDto handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException e) {
        log.error(e.getLocalizedMessage());
        return ExceptionResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .errorMessage(e.getLocalizedMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponseDto handleRuntimeException(
            Exception e) {
        log.error(e.getLocalizedMessage());
        return ExceptionResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .errorMessage(e.getLocalizedMessage())
                .build();
    }
}
