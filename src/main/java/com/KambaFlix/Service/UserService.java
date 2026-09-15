package com.KambaFlix.Service;

import com.KambaFlix.Entity.User;
import com.KambaFlix.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository repository;
    public PasswordEncoder passwordEncoder;

    public User save(User user){
        String password = user.getPassword();
        user.setPassword(password);
        return  repository.save(user);
    }
}
