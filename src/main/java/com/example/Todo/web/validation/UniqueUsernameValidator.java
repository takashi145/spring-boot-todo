package com.example.Todo.web.validation;

import com.example.Todo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // ユーザ名が存在すればtrue
        return userRepository.findByUsername(value).isEmpty();
    }
}
