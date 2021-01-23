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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserController {


    private final PostService postService;
    private final UserService userService;

    @GetMapping(Mappings.USER)
    public String profile(@RequestParam Long id, Model model){

        model.addAttribute("post", new Post());
        model.addAttribute("posts", postService.postsOf(id));
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return ViewNames.USER;
    }
}
