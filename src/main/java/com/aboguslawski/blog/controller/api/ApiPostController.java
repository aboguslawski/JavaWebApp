package com.aboguslawski.blog.controller.api;

import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.dto.PostDTO;
import com.aboguslawski.blog.model.entity.Tag;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.entity.User;
import com.aboguslawski.blog.model.service.TagService;
import com.aboguslawski.blog.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/post")
public class ApiPostController {

    private final PostService postService;
    private final UserService userService;
    private final TagService tagService;

    @GetMapping("/all")
    public Iterable<Post> getAll() {
        return postService.allPosts();
    }

    @GetMapping("/allsorted")
    public List<Post> getAllSorted(@RequestParam String sortBy, @RequestParam boolean ascending) {
        ArrayList<Post> posts = new ArrayList<>(postService.allPosts());

        if(sortBy.equals("id")){
            log.info("sort posts by id");
            posts.sort(Comparator.comparing(Post::getId));
        }

        if(sortBy.equals("title")){
            log.info("sort posts by title");
            posts.sort(Comparator.comparing(Post::getTitle));
        }

        if(sortBy.equals("content")){
            log.info("sort posts by content");
            posts.sort(Comparator.comparing(Post::getContent));
        }

        if(sortBy.equals("date")){
            log.info("sort posts by date");
            posts.sort(Comparator.comparing(Post::getPublicatedAt));
        }

        if(!ascending){
            log.info("posts reversed");
            Collections.reverse(posts);
        }

        return posts;
    }

    @GetMapping
    public Post getById(@RequestParam long id) {
        return postService.getById(id);
    }

    @PostMapping
    public String create(@RequestBody PostDTO postDTO) {

        AtomicBoolean nulls = new AtomicBoolean(false);
//        String info = "following users haven't been found: ";
        AtomicReference<String> info = new AtomicReference<>("following users haven't been found: ");

        List<User> users = postDTO.getAuthorsList()
                .stream()
                .peek(u -> {
                    if(userService.findByEmail(u) == null){
                        nulls.set(true);
                        info.set(info + u + " ");
                    }
                })
                .map(userService::findByEmail)
                .collect(Collectors.toList());

        if(nulls.get()){
            return info.get();
        }

        Post p = new Post(postDTO.getTitle(), postDTO.getContent());

        List<Tag> tags = postDTO.getTagsList()
                .stream()
                .map(tagService::use)
                .collect(Collectors.toList());

        postService.addPost(p, users, tags);

        return "created post";
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
