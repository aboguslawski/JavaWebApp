package com.aboguslawski.blog.registration;

import com.aboguslawski.blog.model.user.User;
import com.aboguslawski.blog.model.user.UserRole;
import com.aboguslawski.blog.model.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator
                .test(request.getEmail());

        if(!isValidEmail){
            throw new IllegalStateException("email is no valid");
        }
        return userService.singUpUser(new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                UserRole.USER
        ));
    }
}
