//package com.aboguslawski.blog.service;
//
//import com.aboguslawski.blog.model.OldPost;
//import com.aboguslawski.blog.model.OldPostData;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Slf4j
//@Service
//public class PostServiceImpl implements PostService {
//
//    // == fields ==
//    @Getter
//    private final OldPostData oldPostData = new OldPostData();
//    private boolean ascending = true;
//
//    // == public methods ==
//    @Override
//    public void addPost(OldPost oldPost) {
//        oldPostData.addPost(oldPost);
//    }
//
//    @Override
//    public void removePost(int id) {
//        oldPostData.removePost(id);
//    }
//
//    @Override
//    public OldPost getPost(int id) {
//        return oldPostData.getPost(id);
//    }
//
//    @Override
//    public void updatePost(OldPost oldPost) {
//        oldPostData.updatePost(oldPost);
//    }
//
//    @Override
//    public List<OldPost> search(String user, String content) {
//        List<OldPost> output = oldPostData.getOldPosts();
//
//        if (user != null && user.length() > 0) {
//            output = filterPostsByUser(user, output);
//        }
//        if (content != null) {
//            output = filterPostsByContent(content, output);
//        }
//        return output;
//    }
//
//    @Override
//    public void sort() {
//        oldPostData.getOldPosts().sort((p1, p2) -> p1.getUser().compareToIgnoreCase(p2.getUser()));
//        if (ascending){
//            Collections.reverse(oldPostData.getOldPosts());
//        }
//        ascending = !ascending;
//    }
//
//
//    // == private methods ==
//    private List<OldPost> filterPostsByUser(String user, List<OldPost> source) {
//        List<OldPost> output = new ArrayList<>();
//
//        for (OldPost p : source) {
//            if (p.getUser().equalsIgnoreCase(user)) {
//                output.add(p);
//            }
//        }
//        return output;
//    }
//
//    private List<OldPost> filterPostsByContent(String content, List<OldPost> source) {
//        List<OldPost> output = new ArrayList<>();
//
//        for (OldPost p : source) {
//            if (p.getContent().contains(content)) {
//                output.add(p);
//            }
//        }
//        return output;
//    }
//
//}
