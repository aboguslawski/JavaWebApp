package com.aboguslawski.blog.model.dto;

import com.aboguslawski.blog.model.validation.AuthorsConstraint;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PostDTO {

    @NotNull(message = "required")
    @Size(min = 1, message = "title can't be empty")
    private String title;

    @NotNull(message = "required")
    @Size(min = 1, message = "content can't be empty")
    private String content;

    @AuthorsConstraint
    private String authors;

    public List<String> getAuthorsList() {
        return Arrays.asList(authors.split(","))
                .stream()
                .map(a -> a.replaceAll("\\s+", ""))
                .collect(Collectors.toList());
    }
}
