package com.maperz.githubreposearch.dto.github;

import lombok.Data;


public record Repo(String name, Owner owner, Boolean fork) {
}
