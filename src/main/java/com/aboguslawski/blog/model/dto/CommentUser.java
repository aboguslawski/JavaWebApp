package com.aboguslawski.blog.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUser {

    private String content;
    private Long post;
    private String author;
}
