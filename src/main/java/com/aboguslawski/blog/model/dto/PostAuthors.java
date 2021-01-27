package com.aboguslawski.blog.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class PostAuthors {
    private String title;
    private String content;
    private String authors;

    public List<String> getAuthors() {
        return Arrays.asList(authors.split(","));
    }
}
