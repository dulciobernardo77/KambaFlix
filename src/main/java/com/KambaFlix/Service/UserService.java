package com.KambaFlix.Service;

import com.KambaFlix.Controller.request.UserRequest;
import com.KambaFlix.Controller.response.UserResponse;
import com.KambaFlix.Entity.User;
import com.KambaFlix.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository repository;

    public User save(User user){
        return  repository.save(user);
    }
}
