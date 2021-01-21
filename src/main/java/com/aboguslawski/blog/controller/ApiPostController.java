package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import com.aboguslawski.blog.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/post")
public class ApiPostController {

    private final PostService postService;

    @GetMapping("/all")
    public Iterable<Post> getAll() {
        return postService.allPosts();
    }

    @GetMapping
    public Optional<Post> getById(@RequestParam long id) {
        return postService.getById(id);
    }

    @PostMapping
    public boolean create(@RequestBody Post post, @RequestBody List<User> authors) {
        postService.addPost(post, authors);

        return true;
    }


    @PutMapping
    public boolean update(@RequestBody Post post) {
        postService.savePost(post);

        return true;
    }

    @PostMapping("/disable")
    public boolean delete(@RequestParam long id) {
        postService.deleteById(id);

        return true;
    }

}
