//package com.aboguslawski.blog.controller;
//
//import com.aboguslawski.blog.model.post.Post;
//import com.aboguslawski.blog.util.AttributeNames;
//import com.aboguslawski.blog.util.Mappings;
//import com.aboguslawski.blog.util.ViewNames;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//
//@Controller
//@Slf4j
//public class AddPostController {
//    // == fields ==
////    private final UserService userService;
////    private final PostService postService;
//
//    // == constructors ==
////    @Autowired
////    public AddPostController(PostService postService) {
////        this.postService = postService;
////    }
//
//    // == methods ==
//
//    @GetMapping(Mappings.NEW_POST)
//    public String addPost(Model model) {
//        model.addAttribute(AttributeNames.POST, new Post());
//        return ViewNames.NEW_POST;
//    }
//
//    @PostMapping(Mappings.NEW_POST)
//    public String processPost(@Valid Post oldPost, Errors errors) {
//        if (errors.hasErrors()) {
//            return ViewNames.NEW_POST;
//        }
//
////        postService.Post(oldPost);
//
//        return "redirect:" + Mappings.HOME;
//    }
//}
