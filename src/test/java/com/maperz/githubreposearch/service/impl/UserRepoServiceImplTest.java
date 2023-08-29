package com.maperz.githubreposearch.service.impl;

import com.maperz.githubreposearch.exceptions.GithubUserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRepoServiceImplTest {

    @Test
    void getUserRepos_reciveGithubUserNotFoundException() {
        //given
        var mockGithubService = mock(GithubServiceImpl.class);
        when(mockGithubService.getRepos("user")).thenThrow(new GithubUserNotFoundException("12345"));

        var toTest = new UserRepoServiceImpl(mockGithubService);
        //when
        var exception = catchThrowable(() -> toTest.getUserRepos("user"));
        //then
        assertThat(exception)
                .isInstanceOf(GithubUserNotFoundException.class)
                .hasMessage("12345");

    }

}