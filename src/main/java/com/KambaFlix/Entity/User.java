package com.KambaFlix.Entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String  name ;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}
