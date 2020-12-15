package com.aboguslawski.blog.service;

import com.aboguslawski.blog.model.User;
import com.aboguslawski.blog.model.UserData;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    // == fields ==
    @Getter
    private final UserData userData = new UserData();

    // == public methods ==

    @Override
    public void addUser(User user) {
        userData.addUser(user);
    }

    @Override
    public void removeUser(int id) {
        userData.removeUser(id);
    }

    @Override
    public User getUser(int id) {
        return userData.getUser(id);
    }

    @Override
    public void updateUser(User user) {
        userData.updateUser(user);
    }
}
