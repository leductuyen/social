package org.example.social.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String title;
    String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}
