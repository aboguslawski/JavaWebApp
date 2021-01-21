package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import com.aboguslawski.blog.model.user.User;
import com.aboguslawski.blog.model.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class ApiUserController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping("/currentUser")
    public String currentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    @GetMapping("/all")
    public Iterable<User> getAll() {
        return userService.allUsers();
    }

    @GetMapping
    public UserDetails getByEmail(@RequestParam String email) {
        return userService.loadUserByUsername(email);
    }

    @PostMapping
    public String create(@RequestBody User user) {
        userService.singUpUser(user);

        return "user created";
    }

    @PutMapping
    public String update(@RequestBody User user) {
        userService.saveUser(user);

        return "user updated";
    }

    @PostMapping("/disable")
    public String delete(@RequestParam String email) {
        userService.disableUser(email);

        return "user removed";
    }

    @GetMapping("/postCount")
    public int postCount(@RequestParam Long id) {
        int i = 0;
        Optional<User> u = userService.findUser(id);

        log.info("found user " + u.get().getEmail());
        for (Post p : postService.allPosts()) {
            if (p.getUsers().contains(u.get())) {
                i++;
            }
        }

        return i;
    }

}
