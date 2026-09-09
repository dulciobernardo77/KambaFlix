package com.KambaFlix.Controller;


import com.KambaFlix.Controller.request.UserRequest;
import com.KambaFlix.Controller.response.UserResponse;
import com.KambaFlix.Entity.User;
import com.KambaFlix.Service.UserService;
import com.KambaFlix.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kambaflix/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService ;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){
       User userSave= userService.save(UserMapper.toUser(request));
       return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(userSave));
    }
}
