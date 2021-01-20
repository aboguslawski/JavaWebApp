//package com.aboguslawski.blog.model;
//
//import com.aboguslawski.blog.util.OpenCSVReader;
//import lombok.NonNull;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ListIterator;
//
//@Slf4j
//public class OldPostData {
//
//    // == fields ==
//    private final List<OldPost> oldPosts = new ArrayList<>();
//
//    // == constructors ==
//    public OldPostData() {
//        try{
//            OpenCSVReader.postsData(oldPosts);
//        }catch (IOException e){
//            addPost(new OldPost(1, "Hilliary Negal", "Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat."));
//            addPost(new OldPost(2, "Saleem Voaden", "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat."));
//            addPost(new OldPost(3, "Stanford Mennithorp", "Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem."));
//            addPost(new OldPost(4, "Celia Legier", "Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros."));
//            addPost(new OldPost(5, "Ilyssa Bowater", "Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam."));
//        }
//    }
//
//    // == public methods ==
//    public List<OldPost> getOldPosts() {
//        return oldPosts;
//    }
//
//    public void addPost(@NonNull OldPost oldPost) {
//        oldPosts.add(oldPost);
//        try{
//            OpenCSVReader.addPost(oldPost);
//            log.info("added post of " + oldPost.getUser());
//        }catch (IOException e){
//            log.info("post couldn't be added to database");
//        }
//    }
//
//    public void removePost(int id) {
//        ListIterator<OldPost> postIterator = oldPosts.listIterator();
//
//        while (postIterator.hasNext()) {
//            OldPost oldPost = postIterator.next();
//
//            if (oldPost.getId() == id) {
//                postIterator.remove();
//                break;
//            }
//        }
//    }
//
//    public OldPost getPost(int id) {
//        for (OldPost p : oldPosts) {
//            if (p.getId() == id) {
//                return p;
//            }
//        }
//        return null;
//    }
//
//    public void updatePost(@NonNull OldPost toUpdate) {
//        ListIterator<OldPost> postIterator = oldPosts.listIterator();
//
//        while (postIterator.hasNext()) {
//            OldPost oldPost = postIterator.next();
//
//            if (oldPost.equals(toUpdate)) {
//                postIterator.set(toUpdate);
//                break;
//            }
//        }
//
//    }
//}
