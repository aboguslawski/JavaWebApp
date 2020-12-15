package com.aboguslawski.blog.service;

import com.aboguslawski.blog.model.User;
import com.aboguslawski.blog.model.UserData;

public interface UserService {

    void addUser(User user);

    void removeUser(int id);

    User getUser(int id);

    void updateUser(User user);

    UserData getUserData();

}
