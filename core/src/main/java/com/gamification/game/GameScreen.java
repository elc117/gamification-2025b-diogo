package com.gamification.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final Main game;
    private Player player;
    private Questions currentQuestion;
    
    private Texture background;
    private Texture character;
    private Texture parede;
    private Texture plataforma;
    private Texture andaime;
    
    private int currentQuestionIndex;
    private float characterX;
    private float characterY;
    private float plataformaY;
    private float andaimeY;
    private boolean gameOver;
    
    // Variáveis para animação de subida
    private boolean isMovingUp;
    private float targetY;
    private float targetPlataformaY;
    private float targetAndaimeY;
    private float moveSpeed = 150f; 

    public GameScreen(Main game) {
        this.game = game;
        this.currentQuestionIndex = 0;
        this.characterX = 30;
        this.characterY = 5;
        this.plataformaY = 0;
        this.andaimeY = 0;
        this.gameOver = false;
        this.isMovingUp = false;
        this.targetY = 5;
        this.targetPlataformaY = 0;
        this.targetAndaimeY = 0;
    }

    @Override
    public void show() {
        background = new Texture("bg.png");
        character = new Texture("char.png");
        parede = new Texture("parede.png");
        plataforma = new Texture("plataforma.png");
        andaime = new Texture("andaime.png");
        
        player = new Player(3, game);
        currentQuestion = new Questions(currentQuestionIndex, game, player);
        
        // Debug: mostrar tamanho das texturas
        System.out.println("Andaime size: " + andaime.getWidth() + "x" + andaime.getHeight());
        System.out.println("Plataforma size: " + plataforma.getWidth() + "x" + plataforma.getHeight());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        
        // Processar input
        handleInput();
        
        // Desenhar tudo em um único batch
        game.getBatch().begin();
        
        // Desenhar background (640x480)
        game.getBatch().draw(background, 0, 0, 640, 480);
        
        // Desenhar cenário do jogo (lado esquerdo - 320px)
        game.getBatch().draw(parede, 0, 0, 320, 480);
        game.getBatch().draw(andaime, 20, andaimeY);
        game.getBatch().draw(plataforma, 20, plataformaY);
        game.getBatch().draw(character, characterX, characterY);
        
        // Renderizar pergunta
        if (currentQuestion != null && !currentQuestion.isAnswered()) {
            currentQuestion.renderInBatch();
        }
        
        game.getBatch().end();
        
        // Verificar clique em respostas
        if (Gdx.input.justTouched() && currentQuestion != null && !currentQuestion.isAnswered()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            Vector2 clickPosition = new Vector2(touchX, touchY);
            
            boolean correct = currentQuestion.checkAnswer(clickPosition);
            
            if (currentQuestion.isAnswered()) {
                if (correct) {
                    // Resposta correta: iniciar animação de subida e avançar
                    float moveUp = 20f; // Subir 20 pixels
                    targetY = characterY + moveUp;
                    targetPlataformaY = plataformaY + moveUp;
                    isMovingUp = true;
                    // Atualizar animação de subida
                    if (isMovingUp) {
                        float moveAmount = moveSpeed * delta;
                        
                        // Mover personagem
                        if (characterY < targetY) {
                            characterY = Math.min(characterY + moveAmount, targetY);
                        }
                        
                        // Mover plataforma
                        if (plataformaY < targetPlataformaY) {
                            plataformaY = Math.min(plataformaY + moveAmount, targetPlataformaY);
                        }
                        
                        // Verificar se chegou ao destino
                        if (characterY >= targetY && plataformaY >= targetPlataformaY && andaimeY >= targetAndaimeY) {
                            isMovingUp = false;
                        }
                    }

                    // Avançar para próxima pergunta (questões 0, 1, 2, 3)
                    if (currentQuestionIndex < 3) {
                        currentQuestionIndex++;
                        currentQuestion.dispose();
                        currentQuestion = new Questions(currentQuestionIndex, game, player);
                    } else {
                        // Completou a 4ª questão (índice 3) - VITÓRIA!
                        game.setScreen(new VictoryScreen(game));
                        dispose();
                    }
                } else {
                    // Resposta errada: perde vida mas mantém na mesma questão
                    player.loseLife();
                    
                    // Resetar a questão para permitir nova tentativa
                    currentQuestion.resetAnswer();
                    
                    if (player.getLives() <= 0) {
                        gameOver = true;
                        game.setScreen(new GameOverScreen(game));
                        dispose();
                        return;
                    }
                }
            }
        }
    }

    private void handleInput() {
        float speed = 200f * Gdx.graphics.getDeltaTime();
        
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            characterX -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            characterX += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            characterY += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            characterY -= speed;
        }
        
        // Manter personagem dentro da área do jogo (lado esquerdo, 320px de largura)
        characterX = Math.max(0, Math.min(characterX, 300 - 64));
        characterY = Math.max(0, Math.min(characterY, 480 - 64));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        if (background != null) background.dispose();
        if (character != null) character.dispose();
        if (parede != null) parede.dispose();
        if (plataforma != null) plataforma.dispose();
        if (andaime != null) andaime.dispose();
        if (currentQuestion != null) currentQuestion.dispose();
    }
}
