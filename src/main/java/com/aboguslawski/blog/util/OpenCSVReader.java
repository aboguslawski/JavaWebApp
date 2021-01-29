package com.aboguslawski.blog.util;

import com.aboguslawski.blog.model.entity.*;
import com.aboguslawski.blog.model.service.CommentService;
import com.aboguslawski.blog.model.service.PostService;
import com.aboguslawski.blog.model.service.TagService;
import com.aboguslawski.blog.model.service.UserService;
import com.opencsv.CSVReader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OpenCSVReader {

    private static final String USERS_PATH = "C:\\Users\\Adam Bogusławski\\Desktop\\Programowanie\\Java\\blog — kopia (2)\\users.csv";
    private static final String POSTS_PATH = "C:\\Users\\Adam Bogusławski\\Desktop\\Programowanie\\Java\\blog — kopia (2)\\posts.csv";
    private static final String COMMENTS_PATH = "C:\\Users\\Adam Bogusławski\\Desktop\\Programowanie\\Java\\blog — kopia (2)\\comments.csv";

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    private final TagService tagService;

    public void usersData() throws IOException {
        String firstName;
        String lastName;
        String email;
        String password = "password";

        Reader reader = Files.newBufferedReader(Paths.get(USERS_PATH));
        CSVReader csvReader = new CSVReader(reader);
        String[] nextRecord;
        csvReader.readNext();

        while ((nextRecord = csvReader.readNext()) != null) {
            firstName = nextRecord[0];
            lastName = nextRecord[1];
            email = nextRecord[2];

            while (userService.exists(email)){
                email = "1" + email;
            }

            User user = new User(
                    firstName,
                    lastName,
                    email,
                    password,
                    UserRole.USER
            );
            userService.singUpUser(user);
            userService.enableUser(user.getEmail());
        }
    }

    public void postsData() throws IOException {
        String title;
        String content;
        String userIds;
        String tags;

        Reader reader = Files.newBufferedReader(Paths.get(POSTS_PATH));
        CSVReader csvReader = new CSVReader(reader);
        String[] nextRecord;
//        csvReader.readNext();

        while ((nextRecord = csvReader.readNext()) != null) {
            title = nextRecord[0];
            content = nextRecord[1];
            userIds = nextRecord[2];
            tags = nextRecord[3];

            if(title.length() > 250){
                title = title.substring(0, 200);
            }
            if(content.length() > 250){
                content = content.substring(0, 200);
            }

            List<User> users = Arrays.asList(userIds.split(","))
                    .stream()
                    .distinct()
                    .map(Long::parseLong)
                    .map(id -> userService.findUser(id).get())
                    .collect(Collectors.toList());

            List<Tag> tagList = Arrays.asList(tags.split(","))
                    .stream()
                    .distinct()
                    .map(a -> a.replaceAll("\\s+", ""))
                    .map(tagService::use)
                    .collect(Collectors.toList());

            Post p = new Post(title, content);

            postService.addPost(p, users, tagList);
        }
    }

    public void commentsData() throws IOException {
        Long authorId;
        Long postId;
        String content;

        Reader reader = Files.newBufferedReader(Paths.get(COMMENTS_PATH));
        CSVReader csvReader = new CSVReader(reader);
        String[] nextRecord;
        csvReader.readNext();

        while ((nextRecord = csvReader.readNext()) != null) {
//            authorId = Long.parseLong(nextRecord[0]);
            postId = Long.parseLong(nextRecord[1]);
            content = nextRecord[2];

            if(postId > 490L){
                postId = 450L;
            }

            if (content.length() > 250){
                content = content.substring(0, 200);
            }

//            User user = userService.findUser(authorId).get();
            Post post = postService.getById(postId);
            Comment comment = new Comment(content);

            commentService.addComment(comment, post);
        }
    }

//    public static void addPost(OldPost oldPost) throws IOException {
//        CSVWriter writer = new CSVWriter(new FileWriter(POSTS_PATH, true));
//
//        String[] record = (oldPost.getId() + "," + oldPost.getUser() + "," + oldPost.getContent()).split(",");
//
//        writer.writeNext(record);
//
//        writer.close();
//
//    }

}
