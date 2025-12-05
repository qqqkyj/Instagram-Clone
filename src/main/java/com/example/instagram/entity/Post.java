package com.example.instagram.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "posts")
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "image_url")
    private String imageUrl;

    @Builder
    public Post(String content, User user, String imageUrl) {
        this.content = content;
        this.user = user;
        this.imageUrl = imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
