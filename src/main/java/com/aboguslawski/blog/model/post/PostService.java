package com.aboguslawski.blog.model.post;

import com.aboguslawski.blog.model.user.User;
import com.aboguslawski.blog.model.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserService userService;

    public String save(Post post){
        postRepo.save(post);

        return "post saved to repository";
    }

    public String addPost(Post post){
        postRepo.save(post);
//        post.getUsers().addAll(authors);
//        postRepo.save(post);

        for (User u : post.getUsers()) {
            userService.saveUser(u);
        }

        return "post added";
    }

    public Iterable<Post> allPosts() {
        return postRepo.findAll();
    }

    public Optional<Post> getById(long id){
        return postRepo.findById(id);
    }

    public void savePost(Post post) {
        postRepo.save(post);
    }

    public void deleteById(long id) {
        postRepo.deleteById(id);
    }
}
