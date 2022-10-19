package com.example.appquestions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EndQuestionsActivity extends AppCompatActivity {

    private TextView counterCorrects;
    private TextView counterIncorrects;
    private TextView percentageCorrectAnswers;
    private TextView percentageSign;
    private Button buttonAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_questions);

        this.counterCorrects = (TextView) findViewById(R.id.counterCorrects);
        this.counterIncorrects = (TextView) findViewById(R.id.counterIncorrects);
        this.percentageCorrectAnswers = (TextView) findViewById(R.id.percentageCorrectAnswers);
        this.percentageSign = (TextView) findViewById(R.id.percentageSign);
        this.buttonAgain = (Button) findViewById(R.id.buttonAgain);


        if (savedInstanceState != null) {
            this.counterCorrects.setText(savedInstanceState.getString("counterCorrectsText"));
            this.counterIncorrects.setText(savedInstanceState.getString("counterIncorrectsText"));
            this.counterIncorrects.setText(savedInstanceState.getString("percentageCorrectAnswers"));

        }


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.counterCorrects.setText(extras.getString("counterCorrects"));
            this.counterIncorrects.setText(extras.getString("counterIncorrects"));
            this.percentageCorrectAnswers.setText(String.format("%.2f", (extras.getFloat("percentageCorrectAnswers"))));
        }


        if (Float.parseFloat((String) this.percentageCorrectAnswers.getText()) >= 50) {
            this.percentageCorrectAnswers.setTextColor(getColor(R.color.green));
            this.percentageSign.setTextColor(getColor(R.color.green));

        } else {
            this.percentageCorrectAnswers.setTextColor(getColor(R.color.red));
            this.percentageSign.setTextColor(getColor(R.color.red));
        }


        this.buttonAgain.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("counterCorrectsText", (String) this.counterCorrects.getText());
        savedInstanceState.putString("counterIncorrectsText", (String) this.counterIncorrects.getText());
        savedInstanceState.putString("percentageCorrectAnswers", (String) this.percentageCorrectAnswers.getText());
    }
}