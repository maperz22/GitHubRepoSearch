package com.maperz.githubreposearch.service.impl;

import com.maperz.githubreposearch.dto.github.Branch;
import com.maperz.githubreposearch.dto.github.Repo;
import com.maperz.githubreposearch.exception.GithubUserNotFoundException;
import com.maperz.githubreposearch.service.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubServiceImpl implements GithubService {

    private final WebClient webClient;


    public List<Repo> getRepos(String username) {
        try {
        return Stream.of(
                webClient.get()
                        .uri("https://api.github.com/users/" + username + "/repos")
                        .retrieve()
                        .bodyToMono(Repo[].class)
                        .block()
        ).filter(repo -> !repo.fork()).toList();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new GithubUserNotFoundException("User " + username + " not found");
            } else {
            throw e;
            }
        }
    }


    public List<Branch> getBranches(String username, String repoName) {
        return List.of(
                webClient.get()
                        .uri("https://api.github.com/repos/" + username + "/" + repoName + "/branches")
                        .retrieve()
                        .bodyToMono(Branch[].class)
                        .block()
        );
    }

}
