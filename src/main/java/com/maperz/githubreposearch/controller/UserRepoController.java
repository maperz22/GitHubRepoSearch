package com.maperz.githubreposearch.controller;

import com.maperz.githubreposearch.dto.userReposDtos.UserRepo;
import com.maperz.githubreposearch.service.UserRepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1")
@RequiredArgsConstructor
public class UserRepoController {

    private final UserRepoService userRepoService;

    @GetMapping(value = "/{username}/repos",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserRepo>> getUserRepo(@PathVariable String username) {
        return ResponseEntity.ok(userRepoService.getUserRepos(username));
    }

}
