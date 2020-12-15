package com.aboguslawski.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class User {

    // == fields ==
    private int id;
    private String name;

    // == statics ==
//    private static int idVal = 1;
//    private static List<User> users = new ArrayList<>();

    // == constructors ==
    public User(String name) {
        this.name = name;
    }

    // == public methods ==

//    public static User getUserByName(String name){
//
//        // look for user with given name in database
//        for(User u : User.getUsers()){
//
//            // if found, return it instead of creating new instance
//            if(u.getName().equalsIgnoreCase(name))
//                return u;
//        }
//
//        // user not found:
//        // create new object, add it to database and return it
//        User user = new User(name);
//        users.add(user);
//        return user;
//    }
}
