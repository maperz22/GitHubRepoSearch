package com.maperz.githubreposearch.dto.userRepos;

import com.maperz.githubreposearch.dto.github.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRepo {
    private String name;
    private String owner;
    private List<Branch> branches;
}
