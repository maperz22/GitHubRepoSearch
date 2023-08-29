package com.maperz.githubreposearch.dto.githubDtos;

import lombok.Data;

@Data
public class Branch {
    private String name;
    private Commit commit;
}
