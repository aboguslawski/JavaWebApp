package com.aboguslawski.blog.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        boolean digit = false;
        boolean uppercase = false;
        boolean lowercase = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digit = true;
            }
            if (Character.isUpperCase(c)) {
                uppercase = true;
            }
            if (Character.isLowerCase(c)) {
                lowercase = true;
            }
        }

        return digit & uppercase & lowercase;
    }
}
