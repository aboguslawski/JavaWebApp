//package com.aboguslawski.blog.controller;
//
//import com.aboguslawski.blog.service.PostService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Slf4j
//public class BlogController {
//    // == fields ==
//    private final PostService postService;
//
//    // == constructors ==
//    @Autowired
//    public BlogController(PostService postService) {
//        this.postService = postService;
//    }
//
//
//    // == request methods ==
////    @GetMapping(Mappings.HOME)
////    public String listPosts(Model model) {
////        model.addAttribute(AttributeNames.POSTS_LIST, postService.getPostData().getPosts());
////        model.addAttribute(AttributeNames.COUNT, postService.getPostData().getPosts().size());
////        return ViewNames.HOME;
////    }
////
////    @GetMapping(Mappings.ADD_POST)
////    public String addPost(Model model) {
////        model.addAttribute(AttributeNames.POST, new Post());
////        return ViewNames.ADD_POST;
////    }
////
////    @PostMapping(Mappings.ADD_POST)
////    public String processPost(@Valid Post post, Errors errors) {
////        if (errors.hasErrors()) {
////            return ViewNames.ADD_POST;
////        }
////
////        postService.addPost(post);
////
////        return "redirect:" + Mappings.HOME;
////    }
//
////    @GetMapping(Mappings.VIEW_POST)
////    public String viewPost(@RequestParam int id, Model model) {
////        Post post = postService.getPost(id);
////        model.addAttribute(AttributeNames.POST, post);
////
////        return ViewNames.VIEW_POST;
////    }
//
////    @GetMapping(Mappings.DELETE_POST)
////    public String removePost(@RequestParam int id) {
////        postService.removePost(id);
////        return "redirect:" + Mappings.HOME;
////    }
////
////    @GetMapping(Mappings.EDIT_POST)
////    public String editPost(@RequestParam int id, Model model) {
////        Post post = postService.getPost(id);
////        model.addAttribute(AttributeNames.POST, post);
////        return ViewNames.EDIT_POST;
////    }
////
////    @PostMapping(Mappings.EDIT_POST)
////    public String processEdit(@ModelAttribute(AttributeNames.POST) Post post, Model model) {
////        log.info("updated post with id " + post.getId() + ". new content: " + post.getContent());
////        model.addAttribute(AttributeNames.POST, post);
////        postService.updatePost(post);
////        return "redirect:" + Mappings.HOME;
////    }
//}
