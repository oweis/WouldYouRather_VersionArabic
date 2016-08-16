package com.appsgemzi.zouheir.wouldyourather2.model;

/**
 * Created by Zouheir on 22/06/2016.
 */
public class Answer {

    private int id_question;
    private int id_user;
    private String answer;

    public Answer(int id_question, int id_user, String answer) {
        this.id_question = id_question;
        this.id_user = id_user;
        this.answer = answer;
    }

}
