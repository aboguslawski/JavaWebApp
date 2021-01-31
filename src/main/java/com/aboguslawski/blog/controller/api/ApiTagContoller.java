package com.aboguslawski.blog.controller.api;

import com.aboguslawski.blog.model.dto.TagDTO;
import com.aboguslawski.blog.model.entity.Tag;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/tag")
public class ApiTagContoller {

    private TagService tagService;
    private PostService postService;

    @GetMapping("/all")
    public Iterable<Tag> all() {

        return tagService.all();
    }

    @GetMapping
    public Tag get(@RequestParam String tag) {

        return tagService.get(tag);
    }

    @GetMapping("/use")
    public Tag use(@RequestParam String tag) {

        return tagService.use(tag);
    }

    @PostMapping("/add")
    public String add(@RequestBody TagDTO tagDTO) {

        List<Tag> tags =  Arrays.asList(tagDTO.getTag().split(","))
                .stream()
                .map(a -> a.replaceAll("\\s+", ""))
                .map(tagService::use)
                .collect(Collectors.toList());

        return tagService.addToPost(tagDTO);
    }

    @PostMapping()
    public String create(@RequestParam String tag) {

        return tagService.save(tag);
    }

    @DeleteMapping
    public String delete(@RequestParam String tag) {
        return tagService.delete(tag);
    }

}
