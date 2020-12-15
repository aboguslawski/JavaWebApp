package com.aboguslawski.blog.model;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

@Slf4j
public class PostData {

    // == fields ==
    private final List<Post> posts = new ArrayList<>();

    // == constructors ==
    public PostData() {
        addPost(new Post(1, "Hilliary Negal", "Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat."));
        addPost(new Post(2, "Saleem Voaden", "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat."));
        addPost(new Post(3, "Stanford Mennithorp", "Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem."));
        addPost(new Post(4, "Celia Legier", "Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros."));
        addPost(new Post(5, "Ilyssa Bowater", "Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam."));

    }

    // == public methods ==
    public List<Post> getPosts() {
        return Collections.unmodifiableList(posts);
    }

    public void addPost(@NonNull Post post) {
        posts.add(post);
    }

    public void removePost(int id) {
        ListIterator<Post> postIterator = posts.listIterator();

        while (postIterator.hasNext()) {
            Post post = postIterator.next();

            if (post.getId() == id) {
                postIterator.remove();
                break;
            }
        }
    }

    public Post getPost(int id) {
        for (Post p : posts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void updatePost(@NonNull Post toUpdate) {
        ListIterator<Post> postIterator = posts.listIterator();

        while (postIterator.hasNext()) {
            Post post = postIterator.next();

            if (post.equals(toUpdate)) {
                postIterator.set(toUpdate);
                break;
            }
        }

    }
}
