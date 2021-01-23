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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final PostService postService;
    private final UserService userService;

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

    //    @PostMapping(Mappings.SEARCH)
//    public String searchPosts(OldPost oldPost, Model model) {
//        String user = oldPost.getUser();
//        String content = oldPost.getContent();
//        List<OldPost> oldPostList = postService.search(user, content);
//
//        model.addAttribute(AttributeNames.COUNT, oldPostList.size());
//        model.addAttribute(AttributeNames.POSTS_LIST, oldPostList);
//        model.addAttribute(AttributeNames.COUNT, oldPostList.size());
//        return ViewNames.HOME;
//    }

}
