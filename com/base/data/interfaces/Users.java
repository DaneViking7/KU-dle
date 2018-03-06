package com.base.data.interfaces;

import com.base.data.models.User;

import java.util.*;

/**
 * Interface for gson to convert json of users to a HashMap of users
 */
public class Users {

    private HashMap<String, User> user_list;

    /**
     * Constructor, takes a populated hashmap of user objects and stores it in this object
     * @param users, a hashmap
     */
    public Users(HashMap<String, User> users) {
        this.user_list = users;
    }

    /**
     * Just a getter bro
     * @return returns the hashmap of user objects
     */
    public HashMap<String, User> getUsers() {
        return this.user_list;
    }

    /**
     * Takes a hashmap of user objects and SETS (cuz its a setter) to our user_list
     * @param users is a hashmap
     */
    public void setUsers(HashMap<String, User> users) {
        this.user_list = users;
    }
}
