package com.aboguslawski.blog.controller.api;

import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.dto.PostAuthors;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.entity.User;
import com.aboguslawski.blog.model.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/post")
public class ApiPostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping("/all")
    public Iterable<Post> getAll() {
        return postService.allPosts();
    }

    @GetMapping
    public Post getById(@RequestParam long id) {
        return postService.getById(id);
    }

    @PostMapping
    public boolean create(@RequestBody PostAuthors postAuthors) {

        List<User> users = postAuthors.getAuthors()
                .stream()
                .map(userService::findByEmail)
                .collect(Collectors.toList());

        Post p = new Post(postAuthors.getTitle(), postAuthors.getContent());

        postService.addPost(p, users);

        return true;
    }


    @PutMapping
    public boolean update(@RequestBody Post post) {

        Post p = postService.getById(post.getId());
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        postService.savePost(p);

        return true;
    }

    @DeleteMapping
    public boolean delete(@RequestParam long id) {
        postService.deleteById(id);

        return true;
    }

}
