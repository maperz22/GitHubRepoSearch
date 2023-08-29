package com.maperz.githubreposearch.service.impl;

import com.maperz.githubreposearch.exception.GithubUserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRepoServiceImplTest {

    /**
     * Should throw GithubUserNotFoundException when user does not exist
     *
     */
    @Test
    @DisplayName("Should throw GithubUserNotFoundException when user does not exist")
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