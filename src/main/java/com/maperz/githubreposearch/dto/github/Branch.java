package com.maperz.githubreposearch.dto.github;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record Branch(String name, Commit commit) {
}
