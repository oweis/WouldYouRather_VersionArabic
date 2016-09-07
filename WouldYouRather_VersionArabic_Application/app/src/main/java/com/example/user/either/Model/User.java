package com.example.user.either.Model;

import java.util.Date;

/**
 * Created by user on 19/08/2016.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private Date dateCreation;

    public User(int id, String name, String login, String password, Date dateCreation) {
        this.name = name;
        this.id = id;
        this.login = login;
        this.password = password;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
