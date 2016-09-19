package com.example.user.either.Either;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.either.ClientAPI.BackgroundTask;
import com.example.user.either.ClientAPI.RecyclerAdapterQuestion;
import com.example.user.either.Model.Question;
import com.example.user.either.R;

import java.util.ArrayList;
import java.util.Date;

public class ShowMyQuestions extends AppCompatActivity {

    RecyclerView recyclerViewQuestions;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_questions);

            recyclerViewQuestions = (RecyclerView) findViewById(R.id.recyclerViewQuestions);
            layoutManager = new LinearLayoutManager(this);
            recyclerViewQuestions.setLayoutManager(layoutManager);
            recyclerViewQuestions.setHasFixedSize(true);

        QuestionsTask questionsTask = new QuestionsTask(ShowMyQuestions.this);
         questionsTask.execute();
    }

    class QuestionsTask extends AsyncTask<Context, Void, ArrayList<Question>> {

        Context ApplicationContext;
        Activity mActivity;
        ProgressDialog progressDialog;

        public QuestionsTask(ShowMyQuestions showMyQuestions) {

            mActivity = showMyQuestions;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("loading");
            progressDialog.show();
        }


        @Override
        protected void onPostExecute(ArrayList<Question> result) {



            recyclerViewQuestions = (RecyclerView) mActivity.findViewById(R.id.recyclerViewQuestions);
            RecyclerView.Adapter adapter = new RecyclerAdapterQuestion(result);
            recyclerViewQuestions.setAdapter(adapter);

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }


        @Override
        protected ArrayList<Question> doInBackground(Context... params) {
            ArrayList<Question> questions = new ArrayList<>();
            Question question1 = new Question(1, 1, "MyFakeAnswer 1", "MyFakeAnswer 2", new Date());
            Question question2 = new Question(2, 1, "Yeap", "Hell No", new Date());

            questions.add(question1);
            questions.add(question2);
            return questions;
           // BackgroundTask backgroundTask = new BackgroundTask();
            //return backgroundTask.getQuestionsByIdUser();
        }

    }
}
