package com.aboguslawski.blog.model.dto;

import com.aboguslawski.blog.model.entity.Comment;
import com.aboguslawski.blog.model.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsAndComments {
    List<PostDTO> posts;
    List<CommentDTO> coments;
}
