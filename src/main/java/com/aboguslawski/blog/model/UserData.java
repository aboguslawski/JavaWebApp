package com.aboguslawski.blog.model;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class UserData {

    // == fields ==
    private static int idVal = 1;
    private final List<Usr> usrs = new ArrayList<>();

    // == constructors ==
    public UserData(){

    }

    // == public methods ==
    public List<Usr> getUsrs(){
        return Collections.unmodifiableList(usrs);
    }

    public void addUser(@NonNull Usr usr){
        usr.setId(idVal);
        usrs.add(usr);
        idVal++;
    }

    public void removeUser(int id){
        ListIterator<Usr> userIterator = usrs.listIterator();

        while (userIterator.hasNext()) {
            Usr usr = userIterator.next();

            if (usr.getId() == id) {
                userIterator.remove();
                break;
            }
        }
    }

    public Usr getUser(int id){
        for(Usr u : usrs){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    public void updateUser(@NonNull Usr toUpdate){
        ListIterator<Usr> userIterator = usrs.listIterator();

        while(userIterator.hasNext()){
            Usr usr = userIterator.next();

            if(usr.equals(toUpdate)){
                userIterator.set(toUpdate);
                break;
            }
        }
    }

}
