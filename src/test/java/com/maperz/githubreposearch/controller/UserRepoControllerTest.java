package com.maperz.githubreposearch.controller;

import com.maperz.githubreposearch.dto.github.Branch;
import com.maperz.githubreposearch.dto.github.Commit;
import com.maperz.githubreposearch.dto.userRepos.UserRepo;
import com.maperz.githubreposearch.exception.GithubUserNotFoundException;
import com.maperz.githubreposearch.service.UserRepoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserRepoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepoService userRepoService;

    @Test
    void shouldGetDefaultResponse() throws Exception {
        when(userRepoService.getUserRepos("user"))
                .thenReturn(
                        List.of(
                            new UserRepo(
                                    "repo",
                                    "url",
                                    List.of(
                                            new Branch(
                                                    "master",
                                                    new Commit("sadas")
                                            )
                                    )
                            )
                        )
                );
        mockMvc.perform(get("/api/v1/user/repos"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"repo\",\"owner\":\"url\",\"branches\":[{\"name\":\"master\",\"commit\":{\"sha\":\"sadas\"}}]}]"));
    }

    @Test
    void shouldGetErrorUserNotFoundResponse() throws Exception {
        when(userRepoService.getUserRepos("user"))
                .thenThrow(new GithubUserNotFoundException("User not found"));
        mockMvc.perform(get("/api/v1/user/repos"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":\"404\",\"message\":\"User not found\"}"));
    }

}