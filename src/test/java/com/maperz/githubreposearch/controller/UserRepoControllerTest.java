package com.maperz.githubreposearch.controller;

import com.maperz.githubreposearch.dto.github.Branch;
import com.maperz.githubreposearch.dto.github.Commit;
import com.maperz.githubreposearch.dto.userRepos.UserRepo;
import com.maperz.githubreposearch.exception.GithubUserNotFoundException;
import com.maperz.githubreposearch.service.UserRepoService;
import org.junit.jupiter.api.DisplayName;
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

    /**
     *Testing default response from controller
     * First acceptance criteria
     */
    @Test
    @DisplayName("Should get default response")
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

    /**
     *Testing error user not found response
     * Second acceptance criteria
     */
    @Test
    @DisplayName("Should get error user not found response when user does not exist")
    void shouldGetErrorUserNotFoundResponse() throws Exception {
        when(userRepoService.getUserRepos("user"))
                .thenThrow(new GithubUserNotFoundException("User not found"));
        mockMvc.perform(get("/api/v1/user/repos"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":404,\"message\":\"User not found\"}"));
    }

    /**
     *Testing media not acceptable response
     * Third acceptance criteria
     */
    @Test
    @DisplayName("Should get media not acceptable response when accept header is not application/json")
    void shouldGetMediaNotAcceptableResponse() throws Exception {
        mockMvc.perform(get("/api/v1/user/repos")
                .header("Accept", "application/xml"))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().json("{\"status\":406,\"message\":\"Media type application/xml not acceptable. Please use: application/json\"}"));
    }

}