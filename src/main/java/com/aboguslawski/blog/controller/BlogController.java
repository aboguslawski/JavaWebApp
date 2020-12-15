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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class BlogController {
    // == fields ==
    private final UserService userService;
    private final PostService postService;

    // == constructors ==
    @Autowired
    public BlogController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }


    // == request methods ==
    @GetMapping(Mappings.HOME)
    public String listPosts(Model model) {
        model.addAttribute(AttributeNames.POSTS_LIST, postService.getPostData().getPosts());
        return ViewNames.HOME;
    }

    @GetMapping(Mappings.ADD_POST)
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        return ViewNames.ADD_POST;
    }

    @PostMapping(Mappings.ADD_POST)
    public String processPost(@ModelAttribute(AttributeNames.POST) Post post, Model model) {
        model.addAttribute("post", post);
        postService.addPost(post);

        return "redirect:" + Mappings.HOME;
    }

    @GetMapping(Mappings.VIEW_POST)
    public String viewPost(@RequestParam int id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute(AttributeNames.POST, post);

        return ViewNames.VIEW_POST;
    }

    @GetMapping(Mappings.DELETE_POST)
    public String removePost(@RequestParam int id){
        postService.removePost(id);
        return "redirect:" + Mappings.HOME;
    }
}
