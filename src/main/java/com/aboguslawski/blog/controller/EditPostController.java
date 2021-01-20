package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.Post;
import com.aboguslawski.blog.service.PostService;
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
public class EditPostController {
    // == fields ==
    private final PostService postService;

    // == constructors ==
    @Autowired
    public EditPostController(PostService postService) {
        this.postService = postService;
    }

    // == methods ==
    @GetMapping(Mappings.VIEW_POST)
    public String viewPost(@RequestParam int id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute(AttributeNames.POST, post);

        return ViewNames.VIEW_POST;
    }

    @GetMapping(Mappings.DELETE_POST)
    public String removePost(@RequestParam int id) {
        postService.removePost(id);
        return "redirect:" + Mappings.HOME;
    }

    @GetMapping(Mappings.EDIT_POST)
    public String editPost(@RequestParam int id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute(AttributeNames.POST, post);
        return ViewNames.EDIT_POST;
    }

    @PostMapping(Mappings.EDIT_POST)
    public String processEdit(@ModelAttribute(AttributeNames.POST) Post post, Model model) {
        log.info("updated post with id " + post.getId() + ". new content: " + post.getContent());
        model.addAttribute(AttributeNames.POST, post);
        postService.updatePost(post);
        return "redirect:" + Mappings.HOME;
    }
}
