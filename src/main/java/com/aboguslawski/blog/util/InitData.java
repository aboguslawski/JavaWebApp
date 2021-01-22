package com.aboguslawski.blog.util;

import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import com.aboguslawski.blog.model.user.User;
import com.aboguslawski.blog.model.user.UserRole;
import com.aboguslawski.blog.model.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InitData {

    private final PostService postService;
    private final UserService userService;


    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        User first = new User(
                "first",
                "one",
                "e@mail.com",
                "password",
                UserRole.USER
        );
        userService.singUpUser(first);
        userService.enableUser("e@mail.com");

        User second = new User(
                "second",
                "two",
                "b@mail.com",
                "password",
                UserRole.USER
        );
        userService.singUpUser(second);
        userService.enableUser("b@mail.com");


        User third = new User(
                "third",
                "three",
                "d@mail.com",
                "password",
                UserRole.USER
        );
        userService.singUpUser(third);
        userService.enableUser("d@mail.com");

        Post post1 = new Post("first title", "first content");
        List<User> authors1 = new ArrayList<>();
        authors1.add(first);
        authors1.add(second);
        post1.setUsers(authors1);
        postService.addPost(post1);
//        for (User u : authors1) {
//            saveUser(u);
//        }

        Post post2 = new Post("second title", "second content");
        List<User> authors2 = new ArrayList<>();
        authors2.add(third);
        post2.setUsers(authors2);
        postService.addPost(post2);
//        for (User u : authors2){
//            saveUser(u);
//        }


    }
}
