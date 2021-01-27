package com.aboguslawski.blog.registration;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    @NotNull(message = "required")
    @Size(min = 2, message = "Name should be start at least two characters")
    private String firstName;

    @NotNull(message = "required")
    @Size(min = 2, message = "Name should be start at least two characters")
    private String lastName;

    @NotNull(message = "required")
    @Size(min = 2, message = "Name should be start at least two characters")
    private String password;

    @NotNull(message = "required")
    @Size(min = 2, message = "Name should be start at least two characters")
    private String email;

}
