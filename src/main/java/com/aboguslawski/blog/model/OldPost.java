//package com.aboguslawski.blog.model;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//@Data
//@EqualsAndHashCode(of = "id")
//public class OldPost {
//
//    // == fields ==
//    @NotNull(message = "id is required")
//    @Min(value = 1, message = "id must be at least 1")
//    private int id;
//
//    @NotNull(message = "username is required")
//    @Size(min = 2, message = "name should contain at least two characters")
//    private String user;
//
//    @NotNull(message = "content is required")
//    private String content;
//
//    // == constructors ==
//    public OldPost(int id, String user, String content) {
//        this.id = id;
//        this.user = user;
//        this.content = content;
//    }
//
//    public OldPost() {
//        id = 0;
//        user = "no user";
//        content = "no content";
//    }
//}
