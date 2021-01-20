package com.aboguslawski.blog.model.post;

import com.aboguslawski.blog.model.comment.Comment;
import com.aboguslawski.blog.model.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Post {

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> authors;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.authors = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
}
