package com.aboguslawski.blog.service;

import com.aboguslawski.blog.model.Post;
import com.aboguslawski.blog.model.PostData;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    // == fields ==
    @Getter
    private final PostData postData = new PostData();

    // == public methods ==
    @Override
    public void addPost(Post post) {
        postData.addPost(post);
    }

    @Override
    public void removePost(int id) {
        postData.removePost(id);
    }

    @Override
    public Post getPost(int id) {
        return postData.getPost(id);
    }

    @Override
    public void UpdatePost(Post post) {
        postData.updatePost(post);
    }

}
