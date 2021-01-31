package com.aboguslawski.blog.controller.api;

import com.aboguslawski.blog.model.dto.CommentDTO;
import com.aboguslawski.blog.model.entity.Comment;
import com.aboguslawski.blog.model.service.CommentService;
import com.aboguslawski.blog.model.dto.CommentUser;
import com.aboguslawski.blog.model.entity.Post;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.entity.User;
import com.aboguslawski.blog.model.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/comment")
public class ApiCommentController {

    private PostService postService;
    private CommentService commentService;
    private UserService userService;

    @GetMapping("/all")
    public List<CommentDTO> all(){

        List<CommentDTO> result = new ArrayList<>();

        for(Comment comment : commentService.allComments()){
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setContent(comment.getContent());
            commentDTO.setPublicatedAt(comment.getPublicatedAt());
            if(comment.getUser() != null){
                commentDTO.setUser(comment.getUser().getEmail());
            }
            Post post = postService.getByComment(comment);
            if(post != null){
                commentDTO.setPostId(post.getId());
            }

            result.add(commentDTO);
        }

        result = result
                .stream()
                .filter(c -> c.getPostId()!= null)
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping
    public CommentDTO get(@RequestParam Long id){
        Comment comment = commentService.getById(id);
        if (comment == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setPublicatedAt(comment.getPublicatedAt());
        commentDTO.setUser(comment.getUser().getEmail());

        Post post = postService.getByComment(comment);
        if(post != null){
            commentDTO.setPostId(post.getId());
            return commentDTO;
        }

        return null;
    }

    @PostMapping
    public String create(@RequestBody CommentUser commentUser){

//        Comment comment = new Comment(commentUser.getContent());
//        User user = userService.findByEmail(commentUser.getAuthor());
//        if(user == null){
//            return "user not found: " + commentUser.getAuthor();
//        }
//        Post post = postService.getById(commentUser.getPost());
//
//        commentService.addComment(comment, post, user);

//        return "added comment of " + user.getEmail();

        Post post = postService.getById(commentUser.getPost());
        Comment comment= new Comment(commentUser.getContent());
        User user = userService.findByEmail(commentUser.getAuthor());
        commentService.addComment(comment, post, user);

        return "added comment of " + user.getEmail();

//        Post post = postService.getById(comment.getId());
//        Comment c = new Comment(comment.getContent());
//        if(userService.currentUserName().equals("anonymousUser")){
//            commentService.addComment(c, post);
//        }else{
//            User user = userService.currentUser();
//            log.info("comment of " + user.getEmail());
//            commentService.addComment(c, post, user);
////            log.info(user.getEmail() + " comments size : " + user.getComments().size());
//        }
    }

    @PutMapping
    public String update(@RequestBody Comment comment){
        Comment c = commentService.getById(comment.getId());
        c.setContent(comment.getContent());
        commentService.save(c);

        return "new comment " + c.getId() + " content: " + c.getContent();
    }

    @DeleteMapping
    public String delete(@RequestParam Long id){
        Comment comment = commentService.getById(id);
        commentService.delete(comment);

        return "comment " + id + " deleted";
    }
}
