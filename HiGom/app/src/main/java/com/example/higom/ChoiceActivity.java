package com.example.higom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        ImageView categoryImage = (ImageView)findViewById(R.id.categoryImage);
        TextView categoryLabel = (TextView)findViewById(R.id.categoryLabel);
        ImageView backButtonImage = (ImageView)findViewById(R.id.backButtonImage);
        Button backButton = (Button)findViewById(R.id.backButton);
        Button flashcard = (Button)findViewById(R.id.flashcard);
        Button quiz = (Button)findViewById(R.id.quiz);



        int category = getIntent().getIntExtra("CATEGORY_LABEL",0);

        if (category == 0){
            categoryImage.setImageResource(R.drawable.rabbit);
            categoryLabel.setText("동물");
        }else if(category == 1){
            categoryImage.setImageResource(R.drawable.apple);
            categoryLabel.setText("과일");
        }else if(category == 2){
            categoryImage.setImageResource(R.drawable.cake);
            categoryLabel.setText("음식");
        }else if(category == 3){
            categoryImage.setImageResource(R.drawable.dress);
            categoryLabel.setText("옷");
        }

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

        flashcard.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                intent.putExtra("CATEGORY_LABEL", 0);
                startActivity(intent);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("CATEGORY_LABEL", 0);
                startActivity(intent);
            }
        });


    }
}