package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.post.Post;
import com.aboguslawski.blog.model.post.PostService;
import com.aboguslawski.blog.model.user.User;
import com.aboguslawski.blog.model.user.UserService;
import com.aboguslawski.blog.registration.RegistrationRequest;
import com.aboguslawski.blog.registration.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class ApiUserController {

    private final UserService userService;
    private final PostService postService;
    private final RegistrationService registrationService;

    @GetMapping("/currentUser")
    public String currentUserName() {
        return userService.currentUserName();
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
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm (@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

    @PutMapping
    public String update(@RequestBody User user) {
        userService.updateUser(user);

        return "user updated";
    }

    @PostMapping("/disable")
    public String disable(@RequestParam String email) {
        userService.disableUser(email);

        return "user " + email + " disabled";
    }

    @DeleteMapping()
    public String delete(@RequestParam String email){
        return userService.deleteUser(email);
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
