package com.example.user.either.Either;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.either.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPlay(View v){
        startActivity(new Intent(MainActivity.this, Play.class));
    }
    public void openSendQuestion(View v){
        startActivity(new Intent(MainActivity.this, SendQuestion.class));
    }

}
