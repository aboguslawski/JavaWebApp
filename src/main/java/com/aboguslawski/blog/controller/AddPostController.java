package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.Post;
import com.aboguslawski.blog.service.PostService;
import com.aboguslawski.blog.service.UserService;
import com.aboguslawski.blog.util.AttributeNames;
import com.aboguslawski.blog.util.Mappings;
import com.aboguslawski.blog.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class AddPostController {
    // == fields ==
    private final UserService userService;
    private final PostService postService;

    // == constructors ==
    @Autowired
    public AddPostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    // == methods ==

    @GetMapping(Mappings.ADD_POST)
    public String addPost(Model model) {
        model.addAttribute(AttributeNames.POST, new Post());
        return ViewNames.ADD_POST;
    }

    @PostMapping(Mappings.ADD_POST)
    public String processPost(@Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ViewNames.ADD_POST;
        }

        postService.addPost(post);

        return "redirect:" + Mappings.HOME;
    }
}
