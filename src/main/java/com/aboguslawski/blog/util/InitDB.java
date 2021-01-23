package com.aboguslawski.blog.util;

import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import com.aboguslawski.blog.model.user.User;
import com.aboguslawski.blog.model.user.UserRepo;
import com.aboguslawski.blog.model.user.UserRole;
import com.aboguslawski.blog.model.user.UserService;
import com.aboguslawski.blog.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InitDB {

    private final UserService userService;
    private final PostService postService;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {

        User admin = new User(
                "Adam",
                "Boguslawski",
                "adam.boguslawski1998@gmail.com",
                "admin1",
                UserRole.ADMIN
        );
        userService.singUpUser(admin);
        userService.enableUser(admin.getEmail());


        User first = new User(
                "first",
                "one",
                "e@mail.com",
                "password",
                UserRole.ADMIN
        );
        userService.singUpUser(first);
        userService.enableUser(first.getEmail());

        User second = new User(
                "second",
                "two",
                "b@mail.com",
                "password",
                UserRole.USER
        );
        userService.singUpUser(second);
        userService.enableUser(second.getEmail());


        User third = new User(
                "third",
                "three",
                "d@mail.com",
                "password",
                UserRole.USER
        );
        userService.singUpUser(third);
        userService.enableUser(third.getEmail());

        Post post1 = new Post("first title", "first content");
        List<User> authors1 = new ArrayList<>();
        authors1.add(first);
        authors1.add(second);
        postService.addPost(post1, authors1);
        for (User u : authors1) {
            userService.saveUser(u);
        }

        Post post2 = new Post("second title", "second content");
        List<User> authors2 = new ArrayList<>();
        authors2.add(third);
        postService.addPost(post2, authors2);
        for (User u : authors2){
            userService.saveUser(u);
        }


    }
}
