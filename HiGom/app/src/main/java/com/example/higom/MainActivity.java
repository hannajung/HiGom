package com.example.higom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.OptionalDataException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private ImageView questionLabel;
    private ImageView starButton;
    private ImageView backButtonImage;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;
    private Button backButton;
//    private String[] quizData;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;                     // 1번부터 시작

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
//    ArrayList<String> quizArray = new ArrayList<>();
    String[] quizData ={ "grapes","strawberry","apple","banana","pineapple","peach","blueberry",
            "watermelon","lemon","cherry","kiwi","coconut","pomegranate","mango","plum" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getActionBar().hide();

        countLabel = (TextView) findViewById(R.id.countLabel);
        questionLabel = (ImageView) findViewById(R.id.questionLabel);
        starButton = (ImageView) findViewById(R.id.starButton);
        answerBtn1 = (Button) findViewById(R.id.answerBtn1);
        answerBtn2 = (Button) findViewById(R.id.answerBtn2);
        answerBtn3 = (Button) findViewById(R.id.answerBtn3);
        answerBtn4 = (Button) findViewById(R.id.answerBtn4);
        backButtonImage = (ImageView)findViewById(R.id.backButtonImage);
        backButton = (Button)findViewById(R.id.backButton);

        backButtonImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });



//        int category = getIntent().getIntExtra("CATEGORY_LABEL", 0);
//        if (category == 0){
//            quizData = new String[]{"rabbit","lion","tiger","pig","monkey"};
//        }else if(category == 1){
//            quizData = new String[]{"grapes","strawberry","apple","banana","pineapple","peach","blueberry",
//                    "watermelon","lemon","cherry","kiwi","coconut","pomegranate","mango","plum" };
//        }




        // Create quizArray from quizData
        for (int i = 0; i < quizData.length; i++) {
            //prepare array
            ArrayList<String> tempArray = new ArrayList<>();
//            tempArray.add(quizData[i][0]);
//            tempArray.add(quizData[i][1]);

            tempArray.add(quizData[i]); // quiz name
            tempArray.add(quizData[i]); // right answer

            ArrayList existArray = new ArrayList();
            existArray.add(i);

            for(int j=2; j<5; j++) {      // choice 1, 2, 3, 4
                Random rand = new Random();
                int random_value = rand.nextInt(10);   // 0 <= random_value < 16
                if (existArray.contains(random_value)) {
                    j--;
                } else {
                    tempArray.add(quizData[random_value]);
                    existArray.add(random_value);
                }
            }
            quizArray.add(tempArray);
        }
        showNextQuiz();

    }

    public void showNextQuiz(){
        //Update quizCountlabel
        countLabel.setText(quizCount+"/"+quizData.length);
        //Generate random number between 0 and 14 (quizArray's size -1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //Pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        //Set question and right answer
        //Array format
//        questionLabel =setText(quiz.get(0));
        String questionName = "";
        questionName = quiz.get(0);
        int resourceId = getResources().getIdentifier(questionName, "drawable", getPackageName());
        questionLabel.setImageResource(resourceId);
        rightAnswer = quiz.get(1);

        //remove "name" from quiz and shuffle choice
        quiz.remove(0);
        Collections.shuffle(quiz);

        //Set Choices
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        //Remove this quiz from quizArray
        quizArray.remove(randomNum);


    }


    public void checkAnswer(View view){

        //get pushed button
        Button answerBtn = (Button)findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if(btnText.equals(rightAnswer)){
            rightAnswerCount ++;
            alertTitle = "Correct!";
        }else{
            alertTitle = "Wrong...";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer: " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (quizCount == quizData.length){
                    //show result
                    // 여기에서 result activity로 이동할것
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    // 짐 들고
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    intent.putExtra("NUMBER_OF_QUIZ", quizData.length);
                    // 기차 출발
                    startActivity(intent);
                }
                else{
                    quizCount ++;
                    showNextQuiz();

                }
            }
        });
        // 하드웨어 BackKey가 눌러졌을 때, 창이 닫히지 않도록 함
        builder.setCancelable(false);
        // 창을 화면에 표시
        builder.show();

    }


}