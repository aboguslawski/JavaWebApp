package com.aboguslawski.blog.util;

import com.aboguslawski.blog.model.dto.TagDTO;
import com.aboguslawski.blog.model.entity.Comment;
import com.aboguslawski.blog.model.service.CommentService;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.entity.User;
import com.aboguslawski.blog.model.entity.UserRole;
import com.aboguslawski.blog.model.service.TagService;
import com.aboguslawski.blog.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InitDB {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    private final TagService tagService;

    private final OpenCSVReader openCSVReader;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {

        try{
            openCSVReader.usersData();
            openCSVReader.postsData();
//            openCSVReader.commentsData();
        } catch (IOException e){
            log.info("IOException");
        }

        User admin = new User(
                "Adam",
                "Boguslawski",
                "adam.boguslawski1998@gmail.com",
                "admin1",
                UserRole.ADMIN
        );
        userService.singUpUser(admin);
        userService.enableUser(admin.getEmail());
//
//
//        User first = new User(
//                "first",
//                "one",
//                "e@mail.com",
//                "password",
//                UserRole.USER
//        );
//        userService.singUpUser(first);
//        userService.enableUser(first.getEmail());
//
//        User second = new User(
//                "second",
//                "two",
//                "b@mail.com",
//                "password",
//                UserRole.USER
//        );
//        userService.singUpUser(second);
//        userService.enableUser(second.getEmail());
//
//
//        User third = new User(
//                "third",
//                "three",
//                "d@mail.com",
//                "password",
//                UserRole.USER
//        );
//        userService.singUpUser(third);
//        userService.enableUser(third.getEmail());
//
//        Post post1 = new Post("first title", "first content");
//        List<User> authors1 = new ArrayList<>();
//        authors1.add(first);
//        authors1.add(second);
//        postService.addPost(post1, authors1);
//        for (User u : authors1) {
//            userService.saveUser(u);
//        }
//
//        Post post2 = new Post("second title", "second content");
//        List<User> authors2 = new ArrayList<>();
//        authors2.add(third);
//        postService.addPost(post2, authors2);
//        for (User u : authors2) {
//            userService.saveUser(u);
//        }
//
//        Comment comment1 = new Comment("comment1");
//
//        commentService.addComment(comment1, post1, first);
//
//        TagDTO tagDTO = new TagDTO("chess", post1.getId());
//        TagDTO tagDTO2 = new TagDTO("test", post1.getId());
//
//        tagService.addToPost(tagDTO);
//        tagService.addToPost(tagDTO2);

    }
}
