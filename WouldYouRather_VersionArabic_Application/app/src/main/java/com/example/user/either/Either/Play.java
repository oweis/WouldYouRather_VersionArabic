package com.example.user.either.Either;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.either.ClientAPI.BackgroundTask;
import com.example.user.either.Model.Answer;
import com.example.user.either.Model.Question;
import com.example.user.either.Model.User;
import com.example.user.either.R;

import java.util.ArrayList;
import java.util.Date;

public class Play extends AppCompatActivity {

    int position = 0;
    boolean like = false;

    ArrayList<Question> questions;
    Question question;
    User user;
    Answer answer;

    Button buttonLike, buttonAnswer1, buttonAnswer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        PlayTask playTask = new PlayTask(Play.this);
        playTask.execute();

    }

    class PlayTask extends AsyncTask<Context, Void, ArrayList<Question>> {
        Activity mActivity;

        public PlayTask(Play play) {
            mActivity = play;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<Question> doInBackground(Context... params) {

            buttonLike = (Button) findViewById(R.id.buttonLike);
            buttonAnswer1 = (Button) findViewById(R.id.buttonAnswer1);
            buttonAnswer2 = (Button) findViewById(R.id.buttonAnswer2);

            user = setUser();
            questions = createQuestionList();
            return questions;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPostExecute(ArrayList<Question> result) {
            showValues();
        }

    }


    public ArrayList<Question> createQuestionList() {
        BackgroundTask backgroundTask = new BackgroundTask();
        return backgroundTask.createQuestionList();
    }

    public User setUser() {
        //Fake : function serve getting actuel user
        //TODO update it with real one
        User user = new User(1, "Jack Balck", "Jack", "123456", new Date());
        return user;
    }

    public void nextQuestion(View view) {
        saveAnswer(view);
        changeLikeButtonColor();
        position++;
        showValues();
    }

    public void checkForMoreQuestions() {
        if (position + 2 > questions.size()) {
            ArrayList<Question> moreQuestions = createQuestionList();
            questions.addAll(moreQuestions);
        }
    }

    public void showValues() {
        question = questions.get(position);
        showAnswer1();
        showAnswer2();
        checkForMoreQuestions();

    }


    public void saveAnswer(View view) {
        int answerNumber = Integer.parseInt(view.getTag().toString());
        answer = new Answer(question.getId(), user.getId(), answerNumber, like);
        like = false;
    }

    public void showAnswer1() {
        buttonAnswer1.setText(question.getAnswer1());
    }

    public void showAnswer2() {
        buttonAnswer2.setText(question.getAnswer2());
    }

    public void likeQuestion(View v) {
        like = !like;
        changeLikeButtonColor();
    }

    public void changeLikeButtonColor() {
        if (like) {
            buttonLike.setBackgroundColor(Color.BLUE);
        } else buttonLike.setBackgroundColor(Color.WHITE);
    }
}
