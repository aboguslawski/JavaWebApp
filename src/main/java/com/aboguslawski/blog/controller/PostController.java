package com.aboguslawski.blog.controller;

import com.aboguslawski.blog.model.entity.Comment;
import com.aboguslawski.blog.model.service.CommentService;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.entity.User;
import com.aboguslawski.blog.model.service.UserService;
import com.aboguslawski.blog.util.Mappings;
import com.aboguslawski.blog.util.ViewNames;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping(Mappings.POST)
    public String post(@RequestParam Long id, Model model){
        Post post = postService.getById(id);

        log.info(post.getComments().size() + " comments");

        model.addAttribute("comment", new Comment());
        model.addAttribute("post", post);
        model.addAttribute("commentService", commentService);
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return ViewNames.POST;
    }

    @PostMapping(Mappings.POST)
    public String comment(@Valid Comment comment, Errors errors){
        if (errors.hasErrors()) {
            return ViewNames.POST;
        }
        log.info("comment content " + comment.getContent());
        log.info("comment id " + comment.getId());

        Post post = postService.getById(comment.getId());
        Comment c = new Comment(comment.getContent());
        User user = userService.currentUser();

        commentService.addComment(c, post, user);


        return "redirect:/" + ViewNames.POST + "?id=" + post.getId();

    }

    @GetMapping(Mappings.DELETE_POST)
    public String deletePost(@RequestParam Long id, Model model){

        Post post = postService.getById(id);

        for(Comment c : post.getComments()){
            commentService.delete(c);
        }

        postService.deleteById(id);

        model.addAttribute("commentService", commentService);
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return "redirect:/";
    }

    @GetMapping(Mappings.DELETE_COMMENT)
    public String deleteComment(@RequestParam Long id, Model model){

        Comment comment = commentService.getById(id);
        commentService.delete(comment);

        model.addAttribute("commentService", commentService);
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return "redirect:/";
    }

    @GetMapping(Mappings.EDIT_POST)
    public String editPost(@RequestParam Long id, Model model){
        Post post = postService.getById(id);

        model.addAttribute("post", post);
        model.addAttribute("commentService", commentService);
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return ViewNames.EDIT_POST;
    }

    @PostMapping(Mappings.EDIT_POST)
    public String editPostSubmit(@RequestParam Long id, @Valid Post post, Errors errors){
        Post p = postService.getById(id);
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        postService.save(p);

        return "redirect:/" + ViewNames.POST + "?id=" + p.getId();
    }

    @GetMapping(Mappings.EDIT_COMMENT)
    public String editComment(@RequestParam Long id, Model model){
        Comment comment = commentService.getById(id);

        model.addAttribute("comment", comment);
        model.addAttribute("commentService", commentService);
        model.addAttribute("postService", postService);
        model.addAttribute("userService", userService);
        return ViewNames.EDIT_COMMENT;
    }

    @PostMapping(Mappings.EDIT_COMMENT)
    public String editCommentSubmit(@Valid Comment comment, Errors errors){
        Comment c = commentService.getById(comment.getId());
        log.info("" + c + " " + c.getId() + " " + c.getContent());
        c.setContent(comment.getContent());
        commentService.save(c);

        return "redirect:/";
    }
}
