package com.example.instagram.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, length = 30)
    private String username;
    @Column(length = 30)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Role role;
    @Column(length = 200)
    private String bio;

    @Builder
    public User(String username, String password, String email, Role role, String bio, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role != null ? role : Role.USER;
        this.bio = bio;
        this.name = name;
    }

    public void updateProfile(String name, String bio){
        this.name = name;
        this.bio = bio;
    }
}
