package com.example.higom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


//import android.support.v7.app.AppCompatActivity;


import org.w3c.dom.Text;


public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);
        Button retry = (Button) findViewById(R.id.retry);
        Button anotherGame = (Button)findViewById(R.id.anotherGame);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
        int numberOfQuiz = getIntent().getIntExtra("NUMBER_OF_QUIZ", 0);

        //안드로이드 앱이 종료되면 앱이 메모리에 가지고 있던 데이터는 사라지기 때문에 재실행시 필요한 데이터라면 여러 가지 방법으로 저장해 놓아야 합니다.
        //SharedPreferences 사용
//        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
//        int totalScore = sharedPreferences.getInt("TOTAL_SCORE", 0);
//        totalScore += score;

        resultLabel.setText(score + " / " + numberOfQuiz);
        totalScoreLabel.setText("Total Score : " + score);

        // Update total score.
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("TOTAL_SCORE", totalScore);
//        editor.apply();

        retry.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        anotherGame.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}