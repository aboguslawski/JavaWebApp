package com.aboguslawski.blog.model.service;

import com.aboguslawski.blog.model.dto.PostDTO;
import com.aboguslawski.blog.model.entity.Comment;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.entity.Tag;
import com.aboguslawski.blog.model.repository.PostRepo;
import com.aboguslawski.blog.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.LazyInitializationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Setter
@Getter
@Slf4j
public class PostService {

    private final PostRepo postRepo;

    private final UserService userService;

    private static boolean ascending = true;

    public String save(Post post) {
        postRepo.save(post);

        return "post saved to repository";
    }

    public String addPost(Post post, List<User> authors, List<Tag> tags) {
        postRepo.save(post);

        post.getUsers().addAll(authors);
        post.getTags().addAll(tags);
        postRepo.save(post);

        return "post added";
    }

    public String addPost(Post post, List<User> authors) {
        postRepo.save(post);

        post.getUsers().addAll(authors);
        postRepo.save(post);

        return "post added";
    }

    public List<Post> allPosts() {
        return postRepo.findAll();
    }

    public Post getById(long id) {
        if (postRepo.findById(id).isPresent()){
            return postRepo.findById(id).get();
        }
        return null;
    }

    public void savePost(Post post) {
        postRepo.save(post);
    }

    public void deleteById(long id) {
        postRepo.deleteById(id);
        log.info("post deleted");
    }

    public List<Post> sortedPosts() {
        List<Post> posts = new ArrayList<>();
        allPosts().iterator().forEachRemaining(posts::add);
        Collections.sort(posts);

        if (ascending) {
            Collections.reverse(posts);
        }

        return posts;
    }

    public void switchSort() {
        ascending = !ascending;
    }

    public List<Post> filter(List<Post> list, String user, String content) {
        log.info("user: " + user + " content: " + content);

        List<Post> result = new ArrayList<>(list);

        result
                .forEach(p -> log.info(p.getUsers().toString()));
        return result.stream()
                .filter(p -> p.getTitle().contains(content))
                .filter(p -> p.getContent().contains(content))
                .filter(p -> p.getUsers().toString().contains(user))
                .collect(Collectors.toList());

    }

    public List<Post> postsOf(Long id){
        User user = userService.findUser(id).get();
        List<Post> result = new ArrayList<>();

        for(Post p : allPosts()){
            if(p.getUsers().contains(user)){
                result.add(p);
            }
        }

        return result;
    }

    public boolean postOf(Post post, String email){
        log.info(post.getUsers().stream().map(User::getEmail).collect(Collectors.joining()));
        return post.getUsers().stream().map(User::getEmail).collect(Collectors.joining()).contains(email);
    }

    public Post getByComment(Comment comment){
        for(Post post : postRepo.findAll()){
            if(post.getComments().contains(comment)){
                return post;
            }
        }

        return null;
    }

    public PostDTO mapToDTO(Post post){
        List<String> emails = post.getUsers()
                .stream()
                .map(User::getEmail)
                .collect(Collectors.toList());

        String authors = String.join(",", emails);
        String tags =  post.getTags().stream().map(Tag::getTag).collect(Collectors.joining(","));

        PostDTO result = new PostDTO(post.getTitle(), post.getContent(), authors, tags);

        return result;
    }

}
