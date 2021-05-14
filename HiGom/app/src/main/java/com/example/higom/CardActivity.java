package com.example.higom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class CardActivity extends AppCompatActivity {

    private ImageView homeicon;
    private TextView homelabel;
    private ImageView backButtonImage;
    private TextView backButton;
    private ImageView categoryicon;
    private TextView categorylabel;
    private ImageView audioButton;
    private TextView countLabel;
    private ImageView starButton;
    private ImageView rightarrow;
    private ImageView leftarrow;
    private ImageView cardImage;
    private TextView cardLabel;
    private int quizCount = 1;
    private TextToSpeech tts;

    private EditText textforspeech;



    String[] quizData ={ "grapes","strawberry","apple","banana","pineapple","peach","blueberry",
            "watermelon","lemon","cherry","kiwi","coconut","pomegranate","mango","plum" };

    ArrayList<Integer> starArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        homeicon = (ImageView)findViewById(R.id.homeicon);
        homelabel = (TextView)findViewById(R.id.homelabel);
        backButtonImage = (ImageView)findViewById(R.id.backButtonImage);
        backButton = (Button)findViewById(R.id.backButton);
        categoryicon = (ImageView)findViewById(R.id.categoryicon);
        categorylabel = (TextView)findViewById(R.id.categorylabel);
        countLabel = (TextView)findViewById(R.id.countLabel);
        audioButton = (ImageView)findViewById(R.id.audioButton);
        starButton = (ImageView)findViewById(R.id.starButton);
        rightarrow = (ImageView)findViewById(R.id.rightarrow);
        leftarrow = (ImageView)findViewById(R.id.leftarrow);
        cardImage = (ImageView)findViewById(R.id.cardImage);
        cardLabel = (TextView)findViewById(R.id.cardLabel);

        textforspeech = (EditText)findViewById(R.id.textforspeech);


        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener(){
            public void onInit(int status){
                if(status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.US);
                }
            }
        });


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


        for (int i = 0; i < quizData.length; i++) {
            starArray.add(0);
        }

        countLabel.setText(quizCount + "/" + quizData.length);
        starButton.setImageResource(R.drawable.starunclick);

        String questionName = "";
        int quizIndex = quizCount - 1;
        questionName = quizData[quizIndex];
        int resourceId = getResources().getIdentifier(questionName, "drawable", getPackageName());
        cardImage.setImageResource(resourceId);
        cardLabel.setText(quizData[quizIndex]);


        starButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                starButton.setSelected((!starButton.isSelected()));

                if(starButton.isSelected()) {
                    starButton.setImageResource(R.drawable.staronclick);
                    starArray.set(quizCount-1, 1);
                }else{
                    starButton.setImageResource(R.drawable.starunclick);
                    starArray.set(quizCount-1, 0);
                }
            }
        });


        audioButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {


                //음성 톤
                tts.setPitch(0.7f);
                //읽는 속도
                tts.setSpeechRate(1.2f);
                tts.speak(textforspeech.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);            }
        });





        rightarrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (quizCount != quizData.length) {
                    quizCount++;
                    showNextCard();
                }else{
                    quizCount = 1;
                    showNextCard();
                }
            }
        });

        leftarrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (quizCount != 1) {
                    quizCount--;
                    showPrevCard();
                }else {
                    quizCount = quizData.length;
                    showPrevCard();
                }
            }
        });
    }

    // 메모리 누출을 방지하게 위해 TTS를 중지
    protected void onStop() {
        super.onStop();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    public void showNextCard() {
        countLabel.setText(quizCount + "/" + quizData.length);

        if (starArray.get(quizCount-1) == 1){
            starButton.setImageResource(R.drawable.staronclick);
        }else{
            starButton.setImageResource(R.drawable.starunclick);
        }

        String questionName = "";
        int quizIndex = quizCount - 1;
        questionName = quizData[quizIndex];
        int resourceId = getResources().getIdentifier(questionName, "drawable", getPackageName());
        cardImage.setImageResource(resourceId);
        cardLabel.setText(quizData[quizIndex]);
    }

    public void showPrevCard() {
        countLabel.setText(quizCount + "/" + quizData.length);

        if (starArray.get(quizCount-1) == 1){
            starButton.setImageResource(R.drawable.staronclick);
        }else{
            starButton.setImageResource(R.drawable.starunclick);
        }

        String questionName = "";
        int quizIndex = quizCount - 1;
        questionName = quizData[quizIndex];
        int resourceId = getResources().getIdentifier(questionName, "drawable", getPackageName());
        cardImage.setImageResource(resourceId);
        cardLabel.setText(quizData[quizIndex]);
    }

    private void speak(){


    }
}