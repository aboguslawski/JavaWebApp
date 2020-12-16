package com.aboguslawski.blog.service;

import com.aboguslawski.blog.model.Post;
import com.aboguslawski.blog.model.PostData;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    // == fields ==
    @Getter
    private final PostData postData = new PostData();
    private boolean ascending = true;

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
    public void updatePost(Post post) {
        postData.updatePost(post);
    }

    @Override
    public List<Post> search(String user, String content) {
        List<Post> output = postData.getPosts();

        if (user != null && user.length() > 0) {
            output = filterPostsByUser(user, output);
        }
        if (content != null) {
            output = filterPostsByContent(content, output);
        }
        return output;
    }

    @Override
    public void sort() {
        postData.getPosts().sort((p1, p2) -> p1.getUser().compareToIgnoreCase(p2.getUser()));
        if (ascending){
            Collections.reverse(postData.getPosts());
        }
        ascending = !ascending;
    }


    // == private methods ==
    private List<Post> filterPostsByUser(String user, List<Post> source) {
        List<Post> output = new ArrayList<>();

        for (Post p : source) {
            if (p.getUser().equalsIgnoreCase(user)) {
                output.add(p);
            }
        }
        return output;
    }

    private List<Post> filterPostsByContent(String content, List<Post> source) {
        List<Post> output = new ArrayList<>();

        for (Post p : source) {
            if (p.getContent().contains(content)) {
                output.add(p);
            }
        }
        return output;
    }

}
