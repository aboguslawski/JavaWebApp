package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import com.aboguslawski.blog.model.user.User;
import com.aboguslawski.blog.model.user.UserService;
import com.aboguslawski.blog.util.Mappings;
import com.aboguslawski.blog.util.ViewNames;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class NewPostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping(Mappings.NEW_POST)
    public String newPost(@Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ViewNames.NEW_POST;
        }
        List<User> authors = new ArrayList<>();
//        authors.add(userService.currentUser());
        post.getUsers().add(userService.currentUser());
        postService.addPost(post);

        return ViewNames.HOME;
    }
}
