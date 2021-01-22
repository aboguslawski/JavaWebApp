package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import com.aboguslawski.blog.model.user.UserService;
import com.aboguslawski.blog.util.Mappings;
import com.aboguslawski.blog.util.ViewNames;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping(Mappings.HOME)
    public String home(Model model) {
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return ViewNames.HOME;
    }

    @GetMapping(Mappings.NEW_POST)
    public String newPost(Model model) {
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        model.addAttribute("post", new Post());

        return ViewNames.NEW_POST;
    }


}
