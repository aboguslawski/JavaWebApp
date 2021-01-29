package com.aboguslawski.blog.model.service;

import com.aboguslawski.blog.model.entity.Comment;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.entity.User;
import com.aboguslawski.blog.model.repository.CommentRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepo commentRepo;
    private final PostService postService;
    private final UserService userService;

    public String addComment(Comment comment, Post post, User user) {

        post.getComments().add(comment);
        postService.save(post);
        post.getUsers().forEach(userService::saveUser);

        user.getComments().add(comment);
        userService.saveUser(user);

        commentRepo.save(comment);


        return "comment added";
    }

    public String addComment(Comment comment, Post post){
        post.getComments().add(comment);
        postService.save(post);
        commentRepo.save(comment);

        return "comment added - anonymous user";
    }

    public User user(Comment comment) {
        for (User user : userService.allUsers()) {
            if (user.getComments().contains(comment)) {
                return user;
            }
        }

        return userService.findUser(1L).get();
    }

    public String username(Comment comment) {
        for (User user : userService.allUsers()) {
            if (user.getComments().contains(comment)) {
                return user.nickname();
            }
        }

        return "anonymous user";
    }

    public void delete(Comment comment) {
        commentRepo.delete(comment);
        log.info("comment deleted");
    }

    public Comment getById(Long id) {
        if (commentRepo.findById(id).isPresent()) {
            return commentRepo.findById(id).get();
        }
        return null;
    }

    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    public Iterable<Comment> allComments() {
        return commentRepo.findAll();
    }

}
