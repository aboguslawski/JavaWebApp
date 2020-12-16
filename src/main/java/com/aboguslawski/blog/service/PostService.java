package com.aboguslawski.blog.service;

import com.aboguslawski.blog.model.Post;
import com.aboguslawski.blog.model.PostData;

import java.util.List;

public interface PostService {

    void addPost(Post post);

    void removePost(int id);

    Post getPost(int id);

    void updatePost(Post post);

    PostData getPostData();

    List<Post> search(String user, String content);

    void sort();
}
