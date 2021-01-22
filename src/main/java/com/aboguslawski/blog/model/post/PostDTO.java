package com.aboguslawski.blog.model.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long id;

    @NotNull(message = "title is required")
    @Size(max = 256, message = "title can contain max 256 characters")
    private String title;

    @NotNull(message = "content is required")
    @Size(max = 1024, message = "content can contain max 1024 characters")
    private String content;

    private List<String> userEmails;

    private List<Long> commentsIds;

    private LocalDateTime publicatedAt;

    @Override
    public String toString() {
        return title + " - " + userEmails.toString();
    }
}
