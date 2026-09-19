package com.KambaFlix.Entity;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome")
    private  String nome ;
}
