package com.maperz.githubreposearch.service.impl;

import com.maperz.githubreposearch.dto.githubDtos.Branch;
import com.maperz.githubreposearch.dto.githubDtos.Repo;
import com.maperz.githubreposearch.exceptions.GithubUserNotFoundException;
import com.maperz.githubreposearch.service.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubServiceImpl implements GithubService {

    private final RestTemplate restTemplate;


    public List<Repo> getRepos(String username) {
        try {
        return Stream.of(
                restTemplate.getForObject("https://api.github.com/users/" + username + "/repos", Repo[].class))
                .filter(repo -> !repo.getFork())
                .toList();
        } catch (HttpClientErrorException e) {
            if (e.getMessage().contains("User not Found")) {
                throw new GithubUserNotFoundException("User not found");
            } else {
                throw e;
            }
        }
    }


    public List<Branch> getBranches(String username, String repoName) {
        return List.of(
                restTemplate.getForObject("https://api.github.com/repos/" + username + "/" + repoName + "/branches", Branch[].class)
        );
    }

}
