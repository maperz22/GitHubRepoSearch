package com.maperz.githubreposearch.service;

import com.maperz.githubreposearch.dto.github.Branch;
import com.maperz.githubreposearch.dto.github.Repo;

import java.util.List;

public interface GithubService {

    List<Repo> getRepos(String username);

    List<Branch> getBranches(String username, String repoName);


}
