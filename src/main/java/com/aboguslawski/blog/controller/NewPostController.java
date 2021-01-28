package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.dto.PostDTO;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.entity.Tag;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.entity.User;
import com.aboguslawski.blog.model.service.TagService;
import com.aboguslawski.blog.model.service.UserService;
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
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j

public class NewPostController {

    private final PostService postService;
    private final UserService userService;
    private final TagService tagService;

    @GetMapping(Mappings.NEW_POST)
    public String newPost(Model model) {
//        model.addAttribute("postService", postService);
//        model.addAttribute("userService", userService);
        model.addAttribute("postDTO", new PostDTO("", "", "", ""));


        return ViewNames.NEW_POST;
    }

    @PostMapping(Mappings.NEW_POST)
    public String submitPost(@Valid PostDTO postDTO, Errors errors) {

        if (errors.hasErrors()) {
            return ViewNames.NEW_POST;
        }

        List<User> users = postDTO.getAuthorsList()
                .stream()
                .map(userService::findByEmail)
                .collect(Collectors.toList());
        users.add(userService.currentUser());

        List<Tag> tags = postDTO.getTagsList()
                .stream()
                .map(tagService::use)
                .collect(Collectors.toList());

        log.info(users.toString());

        Post p = new Post(postDTO.getTitle(), postDTO.getContent());

        postService.addPost(p, users, tags);

//        Post p = new Post(post.getTitle(), post.getContent());
//        User user = userService.currentUser();
//
//        List<User> authors = new ArrayList<>();
//        authors.add(user);
//
//
//
//        postService.addPost(p, authors);
//        for(User u : authors){
//            userService.saveUser(u);
//        }

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
