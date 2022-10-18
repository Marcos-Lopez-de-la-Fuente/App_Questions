package com.example.appquestions;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton beforeButton;
    private TextView questionTextView;

    private TextView counterCorrects;
    private TextView counterIncorrects;


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
        this.beforeButton = findViewById(R.id.before_button);
        this.questionTextView = findViewById(R.id.question_text_view);

        this.counterCorrects = findViewById(R.id.counterCorrects);
        this.counterIncorrects = findViewById(R.id.counterIncorrects);


        if (savedInstanceState != null) {
            this.counterCorrects.setText(savedInstanceState.getString("counterCorrectsText"));
            this.counterIncorrects.setText(savedInstanceState.getString("counterIncorrectsText"));
            this.currentIndex = savedInstanceState.getInt("currentIndex");

            ArrayList<String> questionsBankResponses = savedInstanceState.getStringArrayList("questionsBankResponses");

            for (int i = 0; i < this.questionsBank.size(); i++) {
                if (questionsBankResponses.get(i).equals("null")) {
                    this.questionsBank.get(i).setResponse(null);
                } else {
                    this.questionsBank.get(i).setResponse(Boolean.getBoolean(questionsBankResponses.get(i)));

                }
            }

        }


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

        this.beforeButton.setOnClickListener(view -> {
            if (this.currentIndex == 0) {
                this.currentIndex = this.questionsBank.size();
            }
            this.currentIndex = this.currentIndex - 1;

            updateQuestion();
        });


        this.updateQuestion();

    }


    private void updateQuestion() {
        int questionTextResId = this.questionsBank.get(this.currentIndex).getTextResId();
        this.questionTextView.setText(questionTextResId);


        this.checkResponse();
    }


    public void checkResponse() {
        if (this.questionsBank.get(this.currentIndex).getResponse() == null) {
            this.trueButton.getBackground().setTint(getColor(R.color.purple_500));
            this.falseButton.getBackground().setTint(getColor(R.color.purple_500));

            this.trueButton.setEnabled(true);
            this.falseButton.setEnabled(true);

        } else if (this.questionsBank.get(this.currentIndex).getAnswer()) {
            this.trueButton.getBackground().setTint(getColor(R.color.green));
            this.falseButton.getBackground().setTint(getColor(R.color.red));

            this.trueButton.setEnabled(false);
            this.falseButton.setEnabled(false);

        } else {
            this.trueButton.getBackground().setTint(getColor(R.color.red));
            this.falseButton.getBackground().setTint(getColor(R.color.green));

            this.trueButton.setEnabled(false);
            this.falseButton.setEnabled(false);
        }


    }


    private void checkAnswer(Boolean userAnswer) {

        this.questionsBank.get(this.currentIndex).setResponse(userAnswer);

        this.checkResponse();

        this.changesForAnswer();
    }


    public void changesForAnswer() {
        int messageResId = R.string.incorrect_toast;

        if (this.questionsBank.get(this.currentIndex).getResponse() == this.questionsBank.get(this.currentIndex).getAnswer()) {
            messageResId = R.string.correct_toast;
            this.counterCorrects.setText(String.valueOf(Integer.parseInt(String.valueOf(this.counterCorrects.getText())) + 1));
        } else {
            this.counterIncorrects.setText(String.valueOf(Integer.parseInt(String.valueOf(this.counterIncorrects.getText())) + 1));

        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("counterCorrectsText", (String) this.counterCorrects.getText());
        savedInstanceState.putString("counterIncorrectsText", (String) this.counterIncorrects.getText());
        savedInstanceState.putInt("currentIndex", this.currentIndex);

        ArrayList<String> questionsBankResponses = new ArrayList<String>();
        for (int i = 0; i < this.questionsBank.size(); i++) {
            questionsBankResponses.add(String.valueOf(this.questionsBank.get(i).getResponse()));
        }
        savedInstanceState.putStringArrayList("questionsBankResponses", questionsBankResponses);

    }
}