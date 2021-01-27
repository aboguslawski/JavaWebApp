package com.aboguslawski.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TagDTO {

    private String tag;
    private Long post;

}
