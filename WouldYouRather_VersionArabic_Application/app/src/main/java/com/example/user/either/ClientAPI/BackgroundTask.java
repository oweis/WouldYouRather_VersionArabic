package com.example.user.either.ClientAPI;

import com.example.user.either.Model.Question;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 08/09/2016.
 */
public class BackgroundTask {

    public ArrayList<Question> createQuestionList() {
        //FAKE : serve get questions from database
        //TODO update with real one
        ArrayList<Question> questions = new ArrayList<>();
        Question question1 = new Question(1, 1, "Lays", "Doritos", new Date());
        Question question2 = new Question(2, 1, "Coca cola", "Pepsi", new Date());
        Question question3 = new Question(3, 1, "Zara", "Gucci", new Date());
        Question question4 = new Question(4, 1, "Asus", "HP", new Date());
        Question question5 = new Question(5, 1, "Be Rich, Die Young", "Be Poor, Live for ever", new Date());

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        return questions;
    }

    public void insertIntoDatabase(Question question){
        //TODO : function  save question into database

    }


    public ArrayList<Question> getQuestionsByIdUser(){
        //TODO : function getQuestionsById
        ArrayList<Question> questions = new ArrayList<>();
        Question question1 = new Question(1, 1, "MyFakeAnswer 1", "MyFakeAnswer 2", new Date());
        Question question2 = new Question(2, 1, "Yeap", "Hell No", new Date());

        questions.add(question1);
        questions.add(question2);

        return questions;
    }

}
