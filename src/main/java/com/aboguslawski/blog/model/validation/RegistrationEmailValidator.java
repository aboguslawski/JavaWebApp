package com.aboguslawski.blog.model.validation;

import com.aboguslawski.blog.email.EmailValidator;
import com.aboguslawski.blog.model.validation.EmailConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistrationEmailValidator implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public void initialize(EmailConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        EmailValidator emailValidator = new EmailValidator();
        return emailValidator.test(email);
    }
}
