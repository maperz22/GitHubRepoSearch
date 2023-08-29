package com.maperz.githubreposearch.service;

import com.maperz.githubreposearch.dto.userRepos.UserRepo;

import java.util.List;

public interface UserRepoService {

    List<UserRepo> getUserRepos(String username);

}
