package com.aboguslawski.blog.model.user;

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
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final UserRepo userRepo;
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

    public Optional<User> findUser(long id) {
        return userRepo.findById(id);
    }


}
