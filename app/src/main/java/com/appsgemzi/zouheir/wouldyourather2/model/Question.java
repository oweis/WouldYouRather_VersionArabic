package com.appsgemzi.zouheir.wouldyourather2.model;

/**
 * Created by Zouheir on 22/06/2016.
 */
public class Question {

    private int id_question;
    private int id_user;
    private String question1;
    private String question2;
    private int vote1;
    private int vote2;
    private int total_votes;

    public Question(int id_question, int id_user, String question1, String question2) {
        this.id_question = id_question;
        this.id_user = id_user;
        this.question1 = question1;
        this.question2 = question2;
    }

    public void addQuestion(Question q){

    }

    public void removeQuestion(Question q){

    }



}
