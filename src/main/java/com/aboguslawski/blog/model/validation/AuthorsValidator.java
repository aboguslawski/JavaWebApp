package com.aboguslawski.blog.model.validation;

import com.aboguslawski.blog.model.service.UserService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AuthorsValidator implements ConstraintValidator<AuthorsConstraint, String> {

    private final UserService userService;

    @Override
    public void initialize(AuthorsConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(s.replaceAll("\\s+", "").equals("")){
            return true;
        }
        List<String> authors = Arrays.asList(s.split(","));
        authors = authors
                .stream()
                .map(a -> a.replaceAll("\\s+", ""))
                .collect(Collectors.toList());

        for (String author : authors) {
            if(!userService.exists(author)){
                return false;
            }
        }


        return true;
    }

}