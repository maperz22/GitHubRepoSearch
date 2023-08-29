package com.maperz.githubreposearch.service.impl;

import com.maperz.githubreposearch.dto.userReposDtos.UserRepo;
import com.maperz.githubreposearch.exceptions.GithubUserNotFoundException;
import com.maperz.githubreposearch.service.UserRepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRepoServiceImpl implements UserRepoService {

    private final GithubServiceImpl githubServiceImpl;

    public List<UserRepo> getUserRepos(String username) {
            return githubServiceImpl.getRepos(username)
                    .stream().map(
                            repo -> new UserRepo(
                                    repo.getName(),
                                    repo.getOwner().getLogin(),
                                    githubServiceImpl.getBranches(
                                            username,
                                            repo.getName())
                            )
                    ).toList();

        }



}
