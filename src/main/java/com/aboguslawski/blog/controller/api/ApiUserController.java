package com.aboguslawski.blog.controller.api;

import com.aboguslawski.blog.model.dto.CommentDTO;
import com.aboguslawski.blog.model.dto.PostDTO;
import com.aboguslawski.blog.model.dto.PostsAndComments;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.service.CommentService;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.entity.User;
import com.aboguslawski.blog.model.service.UserService;
import com.aboguslawski.blog.registration.RegistrationRequest;
import com.aboguslawski.blog.registration.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class ApiUserController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
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

    /* registration*/
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

    @GetMapping("/publications")
    public PostsAndComments publications(@RequestParam String email){
        User user = userService.findByEmail(email);
        PostsAndComments result = new PostsAndComments();

        List<PostDTO> postDTOS = postService.postsOf(user.getId())
                .stream()
                .distinct()
                .map(postService::mapToDTO)
                .collect(Collectors.toList());

        List<CommentDTO> commentDTOS = user.getComments()
                .stream()
                .distinct()
                .filter(commentService::exists)
                .map(commentService::mapToDTO)
                .collect(Collectors.toList());

        result.setPosts(postDTOS);
        result.setComents(commentDTOS);

        return result;
    }

}
