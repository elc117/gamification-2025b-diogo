package com.gamification.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
        
        // Configurar filtros para melhor qualidade ao redimensionar
        for(Texture question : questions){
            question.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }

        currentQuestion = questions[questionIndex];
    }

    private boolean isInside(Vector2 position, float x, float y, float width, float height){
        return position.x >= x && position.x <= x + width &&
               position.y >= y && position.y <= y + height;
    }

    public boolean checkAnswer(Vector2 clickPosition){
        if(answered){
            return false;
        }

        float baseX = 280;
        float baseY = 40;

        if(questionIndex == 0){
            xAnswer = baseX;
            yAnswer = baseY + 55;
            widthAnswer = 180;
            heightAnswer = 55;
            
            incorrectAnswers = new float[][]{
                {baseX, baseY, 180, 55},   
                {baseX, baseY + 110, 180, 55},              
                {baseX, baseY + 165, 180, 55}         
            };
        }else if(questionIndex == 1){
            xAnswer = baseX;
            yAnswer = baseY + 110;
            widthAnswer = 180;
            heightAnswer = 55;
            
            incorrectAnswers = new float[][]{
                {baseX, baseY, 180, 55} 
            };
        }else if(questionIndex >= 2){
            xAnswer = baseX;
            yAnswer = baseY + 110;
            widthAnswer = 180;
            heightAnswer = 55;
            
            incorrectAnswers = new float[][]{
                {baseX, baseY, 180, 55},  
                {baseX, baseY + 55, 180, 55},         
                {baseX, baseY + 165, 180, 55} 
            };
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
    
    public void resetAnswer(){
        answered = false;
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
        // Desenhar pergunta no lado direito da tela (área azul)
        // Tela: 640x480, lado esquerdo: 320px, lado direito: 320px
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
