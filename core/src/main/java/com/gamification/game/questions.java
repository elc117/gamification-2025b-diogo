package com.gamification.game;

public class Questions {
    private String question;
    private String[] answers;
    private int true_answer; 

    public Questions(String q, String[] a, int t){
        question = q;
        answers = a;
        true_answer = t;
    } 

    public String getQuention(){
        return question;
    }

    public String getAnswer(int i){
        return answers[i];
    }

    public boolean cheackAnswer(int answers){
        return answers == true_answer; 
    }
    
}

