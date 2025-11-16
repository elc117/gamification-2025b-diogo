package com.gamification.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Questions {
    private Texture[] questions;
    private boolean correctAnswer;
    private int questionIndex;
    private Main game;
    private Player player; 
    private Vector2 position; 
    private float xAnswer, yAnswer, widthAnswer, heightAnswer;
    private float[][] incorrectAnswers;

    public Questions(int questionIndex, Main game, Player player){
        this.questionIndex = questionIndex;
        this.game = game;
        this.player = player;


        questions = new Texture[]{
            new Texture("question1.png"),
            new Texture("question2.png"),
            new Texture("question3.png"),
            new Texture("question4.png")
        };  

        
    } 



  
}

