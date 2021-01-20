//package com.aboguslawski.blog.service;
//
//import com.aboguslawski.blog.model.Usr;
//import com.aboguslawski.blog.model.UserData;
//import lombok.Getter;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService{
//
//    // == fields ==
//    @Getter
//    private final UserData userData = new UserData();
//
//    // == public methods ==
//
//    @Override
//    public void addUser(Usr usr) {
//        userData.addUser(usr);
//    }
//
//    @Override
//    public void removeUser(int id) {
//        userData.removeUser(id);
//    }
//
//    @Override
//    public Usr getUser(int id) {
//        return userData.getUser(id);
//    }
//
//    @Override
//    public void updateUser(Usr usr) {
//        userData.updateUser(usr);
//    }
//}
