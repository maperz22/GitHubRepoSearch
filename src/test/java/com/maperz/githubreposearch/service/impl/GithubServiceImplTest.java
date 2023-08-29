package com.maperz.githubreposearch.service.impl;

import com.maperz.githubreposearch.exceptions.GithubUserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GithubServiceImplTest {

    @Test
    @DisplayName("Should throw GithubUserNotFoundException when user does not exist")
    void getRepos_throwsGithubUserNotFoundException() {
        //given
        var mockGithubService = mock(GithubServiceImpl.class);
        when(mockGithubService.getRepos("user")).thenThrow(
                new HttpClientErrorException(
                        HttpStatus.NOT_FOUND,
                        "User not found",
                        HttpHeaders.EMPTY,
                        null,
                        null));

        var toTest = new UserRepoServiceImpl(mockGithubService);

        var exception = catchThrowable(() -> toTest.getUserRepos("user"));

        assertThat(exception)
                .isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining("User not found");

    }

}