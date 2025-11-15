package com.gamification.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Questions {
    private Texture[] questions;
    private boolean correctAnswer;
    private int questionIndex;
    private Main game;
    // private player player; // TODO: Criar classe Player
    private Vector2 position; 
    private float xAnswer, yAnswer, widthAnswer, heightAnswer;
    private float[][] incorrectAnswers;

    public Questions(int questionIndex, Main game /*, player player*/){
        this.questionIndex = questionIndex;
        this.game = game;
        // this.player = player;
    } 

  
}

