package com.aboguslawski.blog.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
@NoArgsConstructor
@Entity
public class Post implements Comparable<Post> {

    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private Long id;

    private String title;

    private String content;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_user",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private List<Tag> tags;

    private LocalDateTime publicatedAt;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.users = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.publicatedAt = LocalDateTime.now();
    }

    public String publication() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return publicatedAt.format(formatter);
    }

    @Override
    public int compareTo(Post post) {
        return this.getPublicatedAt().compareTo(post.getPublicatedAt());
    }

    public List<User> getUsers() {
        LinkedHashSet<User> set = new LinkedHashSet<>();
        set.addAll(users);
        users.clear();
        users.addAll(set);

        return users;
    }
}
