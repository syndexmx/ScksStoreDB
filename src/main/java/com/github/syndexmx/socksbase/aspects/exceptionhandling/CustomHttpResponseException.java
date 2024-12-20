package com.github.syndexmx.socksbase.aspects.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomHttpResponseException extends ResponseStatusException {
    public CustomHttpResponseException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
