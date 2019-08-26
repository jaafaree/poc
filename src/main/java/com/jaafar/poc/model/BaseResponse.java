package com.jaafar.poc.model;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 7/11/2019 4:36 PM
 */
public class BaseResponse {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
