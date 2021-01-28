package com.aboguslawski.blog.model.service;

import com.aboguslawski.blog.model.entity.Comment;
import com.aboguslawski.blog.model.entity.User;
import com.aboguslawski.blog.model.repository.CommentRepo;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.repository.PostRepo;
import com.aboguslawski.blog.model.repository.UserRepo;
import com.aboguslawski.blog.model.entity.UserRole;
import com.aboguslawski.blog.registration.token.ConfirmationToken;
import com.aboguslawski.blog.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    /*  Looking for user with given email in database.
     *   If not found, throwing an exception.*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    /*  Signing up user to repository.
     *   1. User is required to have unique email.
     *   2. Confirmation token is sent to given email address.
     *   3. Account is enabled by executing proper GET request with generated token as argument.*/
    public String singUpUser(User user) {

        /* Check if email is already taken.*/
        boolean userExists = userRepo
                .findByEmail(user.getEmail())
                .isPresent();

        /* If so, throw an exception*/
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        /* Encrypt password.*/
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        /* Add user to repository.*/
        userRepo.save(user);

        /* Send confirmation token.*/
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        /*TODO: Send email*/

        return token;
    }

    public String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

    public User currentUser() {
        return userRepo.findByEmail(currentUserName()).get();
    }

    public User findByEmail(String email){
        if(userRepo.findByEmail(email).isPresent()){
            return userRepo.findByEmail(email).get();
        }

        return null;
    }

    public boolean isAdmin(){
        if(userRepo.findByEmail(currentUserName()).isPresent()){
            if (userRepo.findByEmail(currentUserName()).get().getUserRole() == UserRole.ADMIN){
                return true;
            }
        }

        return false;
    }


    public int enableUser(String email) {
        return userRepo.enableUser(email);

    }

    public Iterable<User> allUsers() {
        return userRepo.findAll();
    }

    public void saveUser(User user) {
        userRepo.save(user);
        enableUser(user.getEmail());
    }

    public void disableUser(String email) {
        userRepo.disableUser(email);
    }

    public String deleteUser(String email){
        if(userRepo.findByEmail(email).isPresent()){
            User user = userRepo.findByEmail(email).get();

            List<Comment> comments = commentRepo
                    .findAll()
                    .stream()
                    .filter(c -> user.getComments().contains(c))
                    .collect(Collectors.toList());

            user.getComments().removeAll(comments);

            comments.forEach(commentRepo::delete);

            List<Post> posts = postRepo
                    .findAll()
                    .stream()
                    .filter(p -> p.getUsers().toString().contains(email))
                    .collect(Collectors.toList());

            posts.forEach(p -> {
                    p.setUsers(Collections.emptyList());
                    postRepo.save(p);
            });

            posts.forEach(postRepo::delete);

            userRepo.save(user);
            userRepo.delete(user);
            return "user " + email + " deleted.";
        }

        return "user not present";
    }

    public Optional<User> findUser(long id) {
        return userRepo.findById(id);
    }


    public String updateUser(User user) {
        if(userRepo.findByEmail(user.getEmail()).isPresent()){
            User u = userRepo.findByEmail(user.getEmail()).get();
            u.setPassword(user.getPassword());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());

            userRepo.save(u);
            return "user saved";
        }
        return "user not found";
    }

    public boolean exists(String email){
        return userRepo.findByEmail(email).isPresent();
    }
}
