package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class LoginPageController {
    // == fields ==
    private final PostService postService;

    // == constructors ==
    @Autowired
    public LoginPageController(PostService postService) {
        this.postService = postService;
    }
}
