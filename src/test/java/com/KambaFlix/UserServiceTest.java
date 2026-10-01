package com.KambaFlix;

import com.KambaFlix.Entity.User;
import com.KambaFlix.Repository.UserRepository;
import com.KambaFlix.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService service;

    @Test
    void shouldSaveUser() {
        User user = new User();
        user.setId(1L);
        user.setName("João");
        user.setEmail("joao@email.com");
        user.setPassword("Asadegalinha1");

        when(passwordEncoder.encode("Asadegalinha1"))
                .thenReturn("encodedPassword");
        when(repository.save(user))
                .thenReturn(user);

        User result = service.save(user);

        assertEquals(1L, result.getId());
        assertEquals("João", result.getName());
        assertEquals("joao@email.com", result.getEmail());
        assertEquals("encodedPassword", result.getPassword());

        verify(passwordEncoder).encode("Asadegalinha1");
        verify(repository).save(user);
    }
}
