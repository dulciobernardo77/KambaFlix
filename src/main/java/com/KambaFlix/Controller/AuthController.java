package com.KambaFlix.Controller;

import com.KambaFlix.Config.TokenService;
import com.KambaFlix.Controller.request.LoginRequest;
import com.KambaFlix.Controller.request.UserRequest;
import com.KambaFlix.Controller.response.LoginResponse;
import com.KambaFlix.Controller.response.UserResponse;
import com.KambaFlix.Entity.User;
import com.KambaFlix.Exceptions.UsenameOrPasswordInvalidExceptions;
import com.KambaFlix.Service.UserService;
import com.KambaFlix.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kambaflix/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Registo de utilizadores e autenticação na plataforma.")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenSecurity;


    @PostMapping("/register")
    @Operation(summary = "Registar utilizador", description = "Cria uma nova conta e devolve os dados do utilizador.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Utilizador criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Pedido inválido.")
    })
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        User userSave = userService.save(UserMapper.toUser(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserMapper.toUserResponse(userSave));
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sessão", description = "Autentica o utilizador e devolve um token JWT.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autenticação concluída com sucesso."),
            @ApiResponse(responseCode = "400", description = "Email ou palavra-passe inválidos.")
    })
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    );

            Authentication authenticate =
                    authenticationManager.authenticate(authenticationToken);

            User user = (User) authenticate.getPrincipal();

            String token = tokenSecurity.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));
        }catch (BadCredentialsException ex){
            throw  new UsenameOrPasswordInvalidExceptions("Nome ou senha invalida");
        }
    }
}