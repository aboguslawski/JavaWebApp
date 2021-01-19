package com.aboguslawski.blog.service;

import com.aboguslawski.blog.model.Usr;
import com.aboguslawski.blog.model.UserData;

public interface UserService {

    void addUser(Usr usr);

    void removeUser(int id);

    Usr getUser(int id);

    void updateUser(Usr usr);

    UserData getUserData();

}
