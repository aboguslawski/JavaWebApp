package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.comment.Comment;
import com.aboguslawski.blog.model.comment.CommentService;
import com.aboguslawski.blog.model.comment.CommentUser;
import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import com.aboguslawski.blog.model.user.User;
import com.aboguslawski.blog.model.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("api/comment")
public class ApiCommentController {

    private PostService postService;
    private CommentService commentService;
    private UserService userService;

    @GetMapping("/all")
    public Iterable<Comment> all(){

        return commentService.allComments();
    }

    @GetMapping
    public Comment get(@RequestParam Long id){

        return commentService.getById(id);
    }

    @PostMapping
    public String create(@RequestBody CommentUser commentUser){

        Comment comment = new Comment(commentUser.getContent());
        User user = userService.findByEmail(commentUser.getAuthor());
        Post post = postService.getById(commentUser.getPost());

        commentService.addComment(comment, post, user);

        return "added comment of " + user.getEmail();
    }

    @PutMapping
    public String update(@RequestBody Comment comment){
        Comment c = commentService.getById(comment.getId());
        c.setContent(comment.getContent());
        commentService.save(c);

        return "new comment " + c.getId() + " content: " + c.getContent();
    }

    @DeleteMapping
    public String delete(@RequestParam Long id){
        Comment comment = commentService.getById(id);
        commentService.delete(comment);

        return "comment " + id + " deleted";
    }
}
