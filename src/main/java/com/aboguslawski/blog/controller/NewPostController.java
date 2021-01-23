package com.aboguslawski.blog.controller;

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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j

public class NewPostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping(Mappings.NEW_POST)
    public String newPost(Model model) {
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        model.addAttribute("post", new Post());


        return ViewNames.NEW_POST;
    }

    @PostMapping(Mappings.NEW_POST)
    public String newPost(@Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ViewNames.NEW_POST;
        }

        log.info(post.getTitle());
        log.info(post.getContent());

        Post p = new Post(post.getTitle(), post.getContent());
        User user = userService.currentUser();

        List<User> authors = new ArrayList<>();
        authors.add(user);



        postService.addPost(p, authors);
        for(User u : authors){
            userService.saveUser(u);
        }

        //

//        Post post1 = new Post("first title", "first content");
//        List<User> authors1 = new ArrayList<>();
//        authors1.add(first);
//        authors1.add(second);
//        postService.addPost(post1, authors1);
//        for (User u : authors1) {
//            userService.saveUser(u);
//        }

        return "redirect:/";
    }

}
