package com.maperz.githubreposearch.dto.userRepos;

import com.maperz.githubreposearch.dto.github.Branch;

import java.util.List;



public record UserRepo(String name, String owner, List<Branch> branches) {
}
