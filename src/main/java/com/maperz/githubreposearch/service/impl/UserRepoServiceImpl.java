package com.maperz.githubreposearch.service.impl;

import com.maperz.githubreposearch.dto.userRepos.UserRepo;
import com.maperz.githubreposearch.service.UserRepoService;
import com.maperz.githubreposearch.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRepoServiceImpl implements UserRepoService {

    private final GithubService githubService;

    public List<UserRepo> getUserRepos(String username) {
            return githubService.getRepos(username)
                    .stream().map(
                            repo -> new UserRepo(
                                    repo.name(),
                                    repo.owner().login(),
                                    githubService.getBranches(
                                            username,
                                            repo.name())
                            )
                    ).toList();

        }



}
