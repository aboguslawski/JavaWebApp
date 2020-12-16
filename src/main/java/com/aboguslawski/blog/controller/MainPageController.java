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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class MainPageController {
    // == fields ==
    private final UserService userService;
    private final PostService postService;

    // == constructors ==
    @Autowired
    public MainPageController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    // == request methods ==
    @GetMapping(Mappings.HOME)
    public String listPosts(Model model) {
        List<Post> postList = postService.getPostData().getPosts();

        model.addAttribute(AttributeNames.POST, new Post(1, "", ""));
        model.addAttribute(AttributeNames.POSTS_LIST, postList);
        model.addAttribute(AttributeNames.COUNT, postList.size());
        return ViewNames.HOME;
    }

    @PostMapping(Mappings.SEARCH)
    public String searchPosts(Post post, Model model) {
        String user = post.getUser();
        String content = post.getContent();
        List<Post> postList = postService.search(user, content);

        model.addAttribute(AttributeNames.COUNT, postList.size());
        model.addAttribute(AttributeNames.POSTS_LIST, postList);
        model.addAttribute(AttributeNames.COUNT, postList.size());
        return ViewNames.HOME;
    }

    @PostMapping(Mappings.HOME)
    public String sort(Model model){
        postService.sort();
        List<Post> postList = postService.getPostData().getPosts();

        model.addAttribute(AttributeNames.POST, new Post(1, "", ""));
        model.addAttribute(AttributeNames.POSTS_LIST, postList);
        model.addAttribute(AttributeNames.COUNT, postList.size());

        return ViewNames.HOME;
    }

}
