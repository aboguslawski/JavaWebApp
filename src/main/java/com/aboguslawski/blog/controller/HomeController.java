package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.service.UserService;
import com.aboguslawski.blog.registration.RegistrationRequest;
import com.aboguslawski.blog.registration.RegistrationService;
import com.aboguslawski.blog.util.Mappings;
import com.aboguslawski.blog.util.ViewNames;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class HomeController {

    private final PostService postService;
    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping(Mappings.HOME)
    public String home(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("posts", postService.sortedPosts());
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return ViewNames.HOME;
    }

    @GetMapping(Mappings.SORT)
    public String sort(Model model) {
        postService.switchSort();
        model.addAttribute("post", new Post());
        model.addAttribute("posts", postService.sortedPosts());
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return ViewNames.HOME;
    }

    @PostMapping(Mappings.SEARCH)
    public String search(Post post, Model model) {
        String user = post.getTitle();
        String content = post.getContent();
        List<Post> list = postService.sortedPosts();
        List<Post> result = postService.filter(list, user, content);
        model.addAttribute("post", new Post());
        model.addAttribute("posts", result);
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return ViewNames.HOME;
    }

    @GetMapping(Mappings.REGISTER)
    public String register(Model model) {

        model.addAttribute("registrationRequest", new RegistrationRequest("", "", "", ""));
        return ViewNames.REGISTER;
    }

    @PostMapping(Mappings.REGISTER)
    public String registerSubmit(Model model, @Valid RegistrationRequest registrationRequest, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("registrationRequest",registrationRequest);
            log.info("error during registration");
            return ViewNames.REGISTER;
        }else{
            registrationService.register(registrationRequest);
        }


        return "redirect:/";
    }

}
