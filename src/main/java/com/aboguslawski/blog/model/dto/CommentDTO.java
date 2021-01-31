package com.aboguslawski.blog.model.dto;

import com.aboguslawski.blog.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {
    private Long id;
    private Long postId;
    private String content;
    private LocalDateTime publicatedAt;
    private String user;

}
