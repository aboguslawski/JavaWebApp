package com.aboguslawski.blog.service;

import com.aboguslawski.blog.model.Post;
import com.aboguslawski.blog.model.PostData;

public interface PostService {

    void addPost(Post post);

    void removePost(int id);

    Post getPost(int id);

    void UpdatePost(Post post);

    PostData getPostData();
}
