package com.aboguslawski.blog.registration;

import com.aboguslawski.blog.email.EmailConstraint;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    @NotNull(message = "required")
    @Size(min = 2, message = "at least 2 characters")
    @Size(max = 31, message = "no more than 31 characters")
    private String firstName;

    @NotNull(message = "required")
    @Size(min = 2, message = "at least 2 characters")
    @Size(max = 31, message = "no more than 31 characters")
    private String lastName;

    @NotNull(message = "required")
    @Size(min = 6, message = "at least 6 characters")
    @Size(max = 15, message = "no more than 15 characters")
    private String password;

    @NotNull(message = "required")
    @Size(min = 3, message = "at least 3 characters")
    @Size(max = 31, message = "no more than 31 characters")
    @EmailConstraint
    private String email;

}
