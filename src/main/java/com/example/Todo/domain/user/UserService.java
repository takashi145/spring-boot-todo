package com.example.Todo.domain.user;

import com.example.Todo.config.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    public final UserRepository userRepository;

    @Transactional
    public void create(String username, String password) {
        String hash_password = passwordEncoder.encode(password);
        userRepository.insert(username, hash_password);
    }
}
