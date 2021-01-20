package com.aboguslawski.blog.model.post;

import com.aboguslawski.blog.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepo postRepo;

    public String addPost(Post post, List<User> authors){
        postRepo.save(post);

        post.getAuthors().addAll(authors);
        postRepo.save(post);

        return "post added";
    }
}
