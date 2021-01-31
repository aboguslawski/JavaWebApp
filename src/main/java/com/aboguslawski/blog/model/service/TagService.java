package com.aboguslawski.blog.model.service;

import com.aboguslawski.blog.model.dto.TagDTO;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.entity.Tag;
import com.aboguslawski.blog.model.repository.TagRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TagService {

    private final TagRepo tagRepo;
    private final PostService postService;

    public boolean isPresent(String tag) {
        return tagRepo
                .findAll()
                .stream()
                .anyMatch(t -> t.getTag().equals(tag));
    }

    public Tag use(String tag) {
        /* use tag from database if possible*/
        if (isPresent(tag)) {
            return get(tag);
        }

        /* if not, create one and use it*/
        return create(tag);
    }

    public Tag get(String tag) {
        Optional<Tag> result = tagRepo
                .findAll()
                .stream()
                .filter(t -> t.getTag().equals(tag))
                .findFirst();

        return result.orElseThrow(() -> new IllegalStateException("tag not found"));
    }

    public Tag create(String tag) {
        Tag t = new Tag(tag);
        tagRepo.save(t);

        String msg = "created new tag \'" + tag + "\'";
        log.info(msg);
        return t;
    }

    public List<Tag> all(){
        return tagRepo.findAll();
    }

    public String save(String tag){
        tagRepo.save(use(tag));

        String msg = "tag " + tag + " saved";
        log.info(msg);
        return msg;
    }
    public String delete(String tag){
        tagRepo.delete(get(tag));

        return "tag " + tag + " deleted";
    }

    public String addToPost(TagDTO tagDTO) {
        List<Tag> tags =  Arrays.asList(tagDTO.getTag().split(","))
            .stream()
            .map(a -> a.replaceAll("\\s+", ""))
            .map(this::use)
            .collect(Collectors.toList());

        Post post = postService.getById(tagDTO.getPost());

        post.getTags().addAll(tags);
        post.setTags(post.getTags().stream().distinct().collect(Collectors.toList()));
        postService.save(post);

        String msg = "added tags " + tags.toString() + " to post " + post.getId();
        log.info(msg);
        return msg;
    }
}
