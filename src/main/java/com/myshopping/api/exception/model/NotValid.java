package com.myshopping.api.exception.model;

import org.springframework.validation.FieldError;

public record NotValid (
        String field,
        String defaultMessage,
        String rejectedValue
){
    public NotValid(FieldError fieldError){
        this(
                fieldError.getField(),
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue().toString()
        );
    }
}
