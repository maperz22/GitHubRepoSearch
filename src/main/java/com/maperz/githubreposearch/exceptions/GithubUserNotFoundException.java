package com.maperz.githubreposearch.exceptions;

import org.springframework.web.client.HttpClientErrorException;

public class GithubUserNotFoundException extends RuntimeException {
        public GithubUserNotFoundException(String message) {
            super(message);
        }
}
