package com.gamification.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Questions {
    private Texture[] questions;
    private Texture currentQuestion;
    private int questionIndex;
    private Main game;
    private Player player; 
    private float xAnswer, yAnswer, widthAnswer, heightAnswer;
    private float[][] incorrectAnswers;
    private boolean answered;

    public Questions(int questionIndex, Main game, Player player){
        if(questionIndex < 0 || questionIndex > 3){
            throw new IllegalArgumentException("Question index must be between 0 and 3");
        }
        
        this.questionIndex = questionIndex;
        this.game = game;
        this.player = player;
        this.answered = false;

        questions = new Texture[]{
            new Texture("question1.png"),
            new Texture("question2.png"),
            new Texture("question3.png"),
            new Texture("question4.png")
        };  

        currentQuestion = questions[questionIndex];
        if(questionIndex == 0){
            xAnswer = 150;
            yAnswer = 100;
            widthAnswer = 100;
            heightAnswer = 50;

            incorrectAnswers = new float[][]{
                {300, 100, 100, 50},
                {450, 100, 100, 50},
                {600, 100, 100, 50}
            }; 
        }else if(questionIndex == 1){
                xAnswer = 200;
                yAnswer = 120;      
                widthAnswer = 120;
                heightAnswer = 60;
                incorrectAnswers = new float[][]{
                    {350, 120, 120, 60},
                    {500, 120, 120, 60},
                    {650, 120, 120, 60}
                };
        }else if(questionIndex == 2){
                xAnswer = 250;
                yAnswer = 140;      
                widthAnswer = 130;
                heightAnswer = 70;
                incorrectAnswers = new float[][]{
                    {400, 140, 130, 70},
                    {550, 140, 130, 70},
                    {700, 140, 130, 70}
                };
        }else if(questionIndex == 3){
                xAnswer = 300;  
                yAnswer = 160;      
                widthAnswer = 140;  
                heightAnswer = 80;
                incorrectAnswers = new float[][]{
                    {450, 160, 140, 80},
                    {600, 160, 140, 80},
                    {750, 160, 140, 80}
                };
        }
    }

    private boolean isInside(Vector2 position, float x, float y, float width, float height){
        return position.x >= x && position.x <= x + width &&
               position.y >= y && position.y <= y + height;
    }

    public boolean checkAnswer(Vector2 clickPosition){
        if(answered){
            return false;
        }

        // Verifica se clicou na resposta correta
        if(isInside(clickPosition, xAnswer, yAnswer, widthAnswer, heightAnswer)){
            answered = true;
            return true;
        }

        // Verifica se clicou em alguma resposta incorreta
        for(float[] incorrectAnswer : incorrectAnswers){
            if(isInside(clickPosition, incorrectAnswer[0], incorrectAnswer[1], 
                       incorrectAnswer[2], incorrectAnswer[3])){
                answered = true;
                return false;
            }
        }

        return false;
    }

    public boolean isAnswered(){
        return answered;
    }

    public int getQuestionIndex(){
        return questionIndex;
    }

    public void render(){
        game.getBatch().begin();
        game.getBatch().draw(currentQuestion, 100, 200);
        game.getBatch().end();
    }

    public void dispose(){
        for(Texture question : questions){
            if(question != null){
                question.dispose();
            }
        }
    }
}