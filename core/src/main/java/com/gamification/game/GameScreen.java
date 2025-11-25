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

    public GameScreen(Main game) {
        this.game = game;
        this.currentQuestionIndex = 0;
        this.characterX = 30;
        this.characterY = 5;
        this.plataformaY = 0;
        this.andaimeY = 0;
        this.gameOver = false;
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
        if (gameOver) {
            renderGameOver();
            return;
        }
        
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
        
        // Renderizar pergunta (se houver)
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
                    // Resposta correta: mover personagem e plataformas para cima
                    float moveUp = 100f; // Subir 100 pixels
                    characterY += moveUp;
                    plataformaY += moveUp;
                    andaimeY += moveUp;
                    
                    System.out.println("Subindo! Character Y: " + characterY + ", Plataforma Y: " + plataformaY + ", Andaime Y: " + andaimeY);
                } else {
                    player.loseLife();
                    if (player.getLives() <= 0) {
                        gameOver = true;
                    }
                }
                
                // Avançar para próxima pergunta
                if (!gameOver && currentQuestionIndex < 3) {
                    currentQuestionIndex++;
                    currentQuestion.dispose();
                    currentQuestion = new Questions(currentQuestionIndex, game, player);
                } else if (!gameOver) {
                    // Jogador completou todas as perguntas
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
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

    private void renderGameOver() {
        ScreenUtils.clear(0.2f, 0.0f, 0.0f, 1f);
        
        // Aqui você pode adicionar texto "Game Over" quando tiver uma fonte
        // Por enquanto, apenas tela vermelha
        
        // Voltar ao menu após 3 segundos ou ao clicar
        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
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
