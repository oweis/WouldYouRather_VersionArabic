package com.example.user.either.Either;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.either.Model.Question;
import com.example.user.either.R;

import org.w3c.dom.Text;

public class SendQuestion extends AppCompatActivity {
    TextView textViewAnswer1,textViewAnswer2;
    String answer1,answer2;
    Question question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_question);
         textViewAnswer1 = (TextView) findViewById(R.id.textViewAnswer1);
         textViewAnswer2 = (TextView) findViewById(R.id.textViewAnswer2);
    }

    public void saveQuestion(View view){

         answer1 = textViewAnswer1.getText().toString();
         answer2 = textViewAnswer2.getText().toString();
        int userId = 1;
        //TODO : this fake use real userId
         question = new Question(userId,answer1,answer2);
        insertIntoDatabase(question);

        textViewAnswer1.setText("");
        textViewAnswer2.setText("");
        Toast.makeText(getApplicationContext(),"Question created successfully!",Toast.LENGTH_SHORT).show();
    }

    public void insertIntoDatabase(Question question){
        //TODO : function  save question into database
    }
}
