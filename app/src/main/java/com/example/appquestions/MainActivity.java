package com.example.appquestions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private ArrayList<Questions> questionsBank = new ArrayList<Questions>(
            Arrays.asList(
                new Questions(R.string.question_australia, true),
                new Questions(R.string.question_oceans, true),
                new Questions(R.string.question_mideast, false),
                new Questions(R.string.question_africa, false),
                new Questions(R.string.question_americas, true),
                new Questions(R.string.question_asia, true)
            )
    );
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.trueButton = findViewById(R.id.true_button);
        this.falseButton = findViewById(R.id.false_button);
        this.nextButton = findViewById(R.id.next_button);
        this.questionTextView = findViewById(R.id.question_text_view);



        this.trueButton.setOnClickListener(view ->
                checkAnswer(true)
        );

        this.falseButton.setOnClickListener(view ->
                checkAnswer(false)
        );

        this.nextButton.setOnClickListener(view -> {
                this.currentIndex = (this.currentIndex + 1) % this.questionsBank.size();
                updateQuestion();
        });




        updateQuestion();

    }


    private void updateQuestion() {
        int questionTextResId = questionsBank.get(this.currentIndex).getTextResId();
        this.questionTextView.setText(questionTextResId);
    }


    private void checkAnswer(Boolean userAnswer) {
        Boolean correctAnswer = this.questionsBank.get(this.currentIndex).getAnswer();

        int messageResId = R.string.incorrect_toast;
        if (userAnswer == correctAnswer) {
            messageResId = R.string.correct_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}