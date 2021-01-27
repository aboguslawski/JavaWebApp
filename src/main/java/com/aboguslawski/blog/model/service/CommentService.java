package com.aboguslawski.blog.model.service;

import com.aboguslawski.blog.model.entity.Comment;
import com.aboguslawski.blog.model.repository.CommentRepo;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.entity.User;
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

    public String addComment(Comment comment, Post post, User user){
        commentRepo.save(comment);

        post.getComments().add(comment);
        postService.save(post);
        post.getUsers().forEach(userService::saveUser);

        user.getComments().add(comment);
        userService.saveUser(user);

        return "comment added";
    }

    public User user(Comment comment){
        for(User user : userService.allUsers()){
            if(user.getComments().contains(comment)){
                return user;
            }
        }

        return userService.findUser(1L).get();
    }

    public void delete(Comment comment){
        commentRepo.delete(comment);
        log.info("comment deleted");
    }

    public Comment getById(Long id){
        if(commentRepo.findById(id).isPresent()){
            return commentRepo.findById(id).get();
        }
        return null;
    }

    public void save(Comment comment){
        commentRepo.save(comment);
    }

    public Iterable<Comment> allComments() {
        return commentRepo.findAll();
    }

}
