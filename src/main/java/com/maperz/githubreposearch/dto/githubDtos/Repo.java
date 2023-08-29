package com.maperz.githubreposearch.dto.githubDtos;

import lombok.Data;

@Data
public class Repo {
    private String name;
    private Owner owner;
    private Boolean fork;
}
