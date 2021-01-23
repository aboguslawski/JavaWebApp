package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.comment.Comment;
import com.aboguslawski.blog.model.comment.CommentService;
import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import com.aboguslawski.blog.model.user.User;
import com.aboguslawski.blog.model.user.UserService;
import com.aboguslawski.blog.util.Mappings;
import com.aboguslawski.blog.util.ViewNames;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping(Mappings.POST)
    public String post(@RequestParam Long id, Model model){
        Post post = postService.getById(id).get();

        log.info(post.getComments().size() + " comments");

        model.addAttribute("comment", new Comment());
        model.addAttribute("post", post);
        model.addAttribute("commentService", commentService);
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return ViewNames.POST;
    }

    @PostMapping(Mappings.POST)
    public String comment(@Valid Comment comment, Errors errors){
        if (errors.hasErrors()) {
            return ViewNames.POST;
        }
        log.info("comment content " + comment.getContent());
        log.info("comment id " + comment.getId());

        Post post = postService.getById(comment.getId()).get();
        Comment c = new Comment(comment.getContent());
        User user = userService.currentUser();

        commentService.addComment(c, post, user);


        return "redirect:/" + ViewNames.POST + "?id=" + post.getId();

    }
}
