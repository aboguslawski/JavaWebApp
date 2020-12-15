package com.aboguslawski.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Post {

    // == fields ==
    private int id;
    private String user;
    private String content;

    // == constructors ==
    public Post(int id, String user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }
}
