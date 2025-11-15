package com.gamification.game;

public class Questions {
    private Texture[] questions;
    private boolean correctAnswer;
    private int questionIndex;
    private Main game;
    private player player;
    private Vector2 position; 
    private float xAnswer, yAnswer, widthAnswer, heightAnswer;
    private float[][] incorrectAnswers;

    public Questions(int questionIndex, Main game, player player){
        this.questionIndex = questionIndex;
        this.game = game;
        this.player = player;
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

