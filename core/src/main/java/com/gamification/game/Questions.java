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
        
        // Posições ajustadas para questões maiores e centralizadas
        float baseX = 280;
        float baseY = 40;
        
        // Ajustar posições das respostas (assumindo 4 opções em 2x2)
        // Resposta correta sempre na posição superior esquerda
        xAnswer = baseX + 40;
        yAnswer = baseY + 300;
        widthAnswer = 140;
        heightAnswer = 55;

        // Respostas incorretas: superior direita, inferior esquerda, inferior direita
        incorrectAnswers = new float[][]{
            {baseX + 190, baseY + 300, 140, 55},  // Superior direita
            {baseX + 40, baseY + 235, 140, 55},   // Inferior esquerda
            {baseX + 190, baseY + 235, 140, 55}   // Inferior direita
        };
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
        // Centralizar melhor e aumentar tamanho
        float questionX = 280;
        float questionY = 40;
        float questionWidth = 360;
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
