package com.aboguslawski.blog.model.post;

import com.aboguslawski.blog.model.comment.Comment;
import com.aboguslawski.blog.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude="users")
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_user",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    private LocalDateTime publicatedAt;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.users = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.publicatedAt = LocalDateTime.now();
    }

    public String publication(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return publicatedAt.format(formatter);
    }

//    @Override
//    public int compare(Post a, Post b) {
//        return a.getPublicatedAt().compareTo(b.getPublicatedAt());
//    }

    @Override
    public int compareTo(Post post) {
        return this.getPublicatedAt().compareTo(post.getPublicatedAt());
    }

}
