package com.aboguslawski.blog.model;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class UserData {

    // == fields ==
    private static int idVal = 1;
    private final List<User> users = new ArrayList<>();

    // == constructors ==
    public UserData(){

    }

    // == public methods ==
    public List<User> getUsers(){
        return Collections.unmodifiableList(users);
    }

    public void addUser(@NonNull User user){
        user.setId(idVal);
        users.add(user);
        idVal++;
    }

    public void removeUser(int id){
        ListIterator<User> userIterator = users.listIterator();

        while (userIterator.hasNext()) {
            User user = userIterator.next();

            if (user.getId() == id) {
                userIterator.remove();
                break;
            }
        }
    }

    public User getUser(int id){
        for(User u : users){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    public void updateUser(@NonNull User toUpdate){
        ListIterator<User> userIterator = users.listIterator();

        while(userIterator.hasNext()){
            User user = userIterator.next();

            if(user.equals(toUpdate)){
                userIterator.set(toUpdate);
                break;
            }
        }
    }

}
