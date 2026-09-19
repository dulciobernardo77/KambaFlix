package com.KambaFlix.Entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "Streaming")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Streaming {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "nome")
    private  String nome;
}
