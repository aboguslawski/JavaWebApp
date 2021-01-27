package com.aboguslawski.blog.model.service;

import com.aboguslawski.blog.model.entity.Tag;
import com.aboguslawski.blog.model.repository.TagRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TagService {

    private final TagRepo tagRepo;

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

}
