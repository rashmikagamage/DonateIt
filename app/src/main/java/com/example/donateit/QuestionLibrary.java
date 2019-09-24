package com.example.donateit;

public class QuestionLibrary {
    private String mQuestions [] = {
            "What made you go into social work?",
            "Help a client make business decisions?",
            "Help elderly people complete their daily activities?",
            "Help someone with a disability get dressed"

    };


    private String mChoices [][] = {
            {"For fun", "Peer pressure", "I have a desire to help others"},
            {"Dislike it", "Neutral", "Love it"},
            {"Dislike it", "Neutral", "Love it"},
            {"Dislike it", "Neutral", "Love it"}
    };



    private String mCorrectAnswers[] = {"I have a desire to help others", "Neutral", "Neutral", "Neutral"};




    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}

