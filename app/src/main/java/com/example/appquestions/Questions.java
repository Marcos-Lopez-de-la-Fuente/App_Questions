package com.example.appquestions;

public class Questions {
    private int textResId;
    private Boolean answer;
    private Boolean response;

    public Questions(int textResId, Boolean answer) {
        this.textResId = textResId;
        this.answer = answer;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public Boolean getResponse() {
        return this.response;
    }

    public int getTextResId() {
        return this.textResId;
    }

    public Boolean getAnswer() {
        return this.answer;
    }


}