package com.example.user.either.Model;

import java.util.Date;

/**
 * Created by user on 19/08/2016.
 */
public class Question {

    private int id;
    private int userId;
    private String answer1;
    private String answer2;
    private Date dateCreation;

    public Question(int id, int userId, String answer1, String answer2, Date dateCreation) {
        this.userId = userId;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.dateCreation = dateCreation;
        this.id = id;
    }

    public Question(int userId, String answer1, String answer2) {
        this.userId = userId;
        this.answer1 = answer1;
        this.answer2 = answer2;

    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
