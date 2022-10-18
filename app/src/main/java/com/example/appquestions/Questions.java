package com.example.appquestions;

public class Questions {
    private int textResId;
    private Boolean answer;

    public Questions(int textResId, Boolean answer) {
        this.textResId = textResId;
        this.answer = answer;
    }

    public int getTextResId() {
        return this.textResId;
    }

    public Boolean getAnswer() {
        return this.answer;
    }
}
