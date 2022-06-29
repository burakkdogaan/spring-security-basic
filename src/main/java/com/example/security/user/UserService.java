package com.example.security.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User1 save(User1 user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return this.userRepository.save(user);
    }

    public User1 updateUser(long id, User1 user) {
        User1 inDB = userRepository.getOne(id);
        inDB.setDisplayName(user.getDisplayName());
        inDB.setLastUpdated(LocalDateTime.now());
        return this.userRepository.save(inDB);
    }
}
