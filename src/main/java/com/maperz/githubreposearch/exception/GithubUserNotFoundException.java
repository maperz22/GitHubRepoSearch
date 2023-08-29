package com.maperz.githubreposearch.exception;

public class GithubUserNotFoundException extends RuntimeException {
        public GithubUserNotFoundException(String message) {
            super(message);
        }
}
