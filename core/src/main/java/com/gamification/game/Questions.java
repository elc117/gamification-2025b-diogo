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
        
        float baseX = 330;
        float baseY = 40;
        
        if(questionIndex == 0){
            xAnswer = baseX + 20;
            yAnswer = baseY + 60;
            widthAnswer = 120;
            heightAnswer = 30;

            incorrectAnswers = new float[][]{
                {baseX + 20, baseY + 20, 120, 30},
                {baseX + 160, baseY + 60, 120, 30},
                {baseX + 160, baseY + 20, 120, 30}
            }; 
        }else if(questionIndex == 1){
            xAnswer = baseX + 20;
            yAnswer = baseY + 60;
            widthAnswer = 120;
            heightAnswer = 30;
            
            incorrectAnswers = new float[][]{
                {baseX + 20, baseY + 20, 120, 30},
                {baseX + 160, baseY + 60, 120, 30},
                {baseX + 160, baseY + 20, 120, 30}
            };
        }else if(questionIndex == 2){
            xAnswer = baseX + 20;
            yAnswer = baseY + 60;
            widthAnswer = 120;
            heightAnswer = 30;
            
            incorrectAnswers = new float[][]{
                {baseX + 20, baseY + 20, 120, 30},
                {baseX + 160, baseY + 60, 120, 30},
                {baseX + 160, baseY + 20, 120, 30}
            };
        }else if(questionIndex == 3){
            xAnswer = baseX + 20;
            yAnswer = baseY + 60;
            widthAnswer = 120;
            heightAnswer = 30;
            
            incorrectAnswers = new float[][]{
                {baseX + 20, baseY + 20, 120, 30},
                {baseX + 160, baseY + 60, 120, 30},
                {baseX + 160, baseY + 20, 120, 30}
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

        if(isInside(clickPosition, xAnswer, yAnswer, widthAnswer, heightAnswer)){
            answered = true;
            return true;
        }

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
        renderInBatch();
        game.getBatch().end();
    }
    
    public void renderInBatch(){
        float questionX = 330;
        float questionY = 40;
        float questionWidth = 300;
        float questionHeight = 400;
        game.getBatch().draw(currentQuestion, questionX, questionY, questionWidth, questionHeight);
    }

    public void dispose(){
        for(Texture question : questions){
            if(question != null){
                question.dispose();
            }
        }
    }
}
