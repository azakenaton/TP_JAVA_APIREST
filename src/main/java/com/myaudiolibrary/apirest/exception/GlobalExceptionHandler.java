package com.myaudiolibrary.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(
            EntityNotFoundException entityNotFoundException) {
        return entityNotFoundException.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleEntityNotFoundException(
            IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException.getMessage();
    }

    @ExceptionHandler(ArtistAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleArtistAlreadyExistException(
            ArtistAlreadyExistException artistAlreadyExistException) {
        return artistAlreadyExistException.getMessage();
    }



}
