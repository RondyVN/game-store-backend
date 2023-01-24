package com.api.gameshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateGenreInGame extends RuntimeException {
    public DuplicateGenreInGame(String message) {
        super(message);
    }
}
