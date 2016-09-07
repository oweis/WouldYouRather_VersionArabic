package com.example.user.either.Model;

import java.util.Date;

/**
 * Created by user on 19/08/2016.
 */
public class Answer {

    private int id;
    private int questionId;
    private int userId;
    private int answerNumber;
    private boolean like;
    private Date dateCreation = new Date();

    public Answer(int id, int questionId, int userId, int answerNumber,boolean like, Date dateCreation) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.answerNumber = answerNumber;
        this.like = like;
        this.dateCreation = dateCreation;
    }

    public Answer(int questionId, int userId, int answerNumber,boolean like) {
        this.questionId = questionId;
        this.userId = userId;
        this.answerNumber = answerNumber;
        this.like = like;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
