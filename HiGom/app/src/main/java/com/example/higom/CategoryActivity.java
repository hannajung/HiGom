package com.example.higom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Button animalButton= (Button) findViewById(R.id.animalButton);
        Button fruitButton = (Button) findViewById(R.id.fruitButton);
        Button foodButton = (Button) findViewById(R.id.foodButton);
        Button clothButton = (Button) findViewById(R.id.clothButton);

        animalButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
                intent.putExtra("CATEGORY_LABEL", 0);
                startActivity(intent);
            }
        });


        fruitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
                intent.putExtra("CATEGORY_LABEL", 1);
                // 기차 출발
                startActivity(intent);
            }
        });

        foodButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
                intent.putExtra("CATEGORY_LABEL", 2);
                // 기차 출발
                startActivity(intent);
            }
        });

        clothButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
                intent.putExtra("CATEGORY_LABEL", 3);
                // 기차 출발
                startActivity(intent);
            }
        });
    }
}