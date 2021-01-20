package com.aboguslawski.blog.model.comment;

import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final PostService postService;

    public String addComment(Comment comment, Post post){
        commentRepo.save(comment);

        post.getComments().add(comment);
        postService.save(post);

        return "comment added";
    }

}
