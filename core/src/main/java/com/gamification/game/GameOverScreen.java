package com.gamification.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
    final Main game;
    private Texture background;
    private Texture gameOverImage; 
    private BitmapFont font;
    private float timer;
    
    public GameOverScreen(Main game) {
        this.game = game;
        this.timer = 0;
    }

    @Override
    public void show() {
        background = new Texture("bg.png");
        try {
            gameOverImage = new Texture("gameover.png");
            System.out.println("Game Over image loaded: " + gameOverImage.getWidth() + "x" + gameOverImage.getHeight());
        } catch (Exception e) {
            System.err.println("Erro ao carregar gameover.png: " + e.getMessage());
        }
        font = new BitmapFont();
        font.getData().setScale(3);
    }

    @Override
    public void render(float delta) {
        timer += delta;
        
        ScreenUtils.clear(0.2f, 0.0f, 0.0f, 1f);
        
        game.getBatch().begin();
        
        // Desenhar background escurecido
        game.getBatch().setColor(0.3f, 0.3f, 0.3f, 1f);
        game.getBatch().draw(background, 0, 0, 640, 480);
        
        // Resetar cor para branco (cor normal)
        game.getBatch().setColor(1f, 1f, 1f, 1f);
        
        // Desenhar imagem de game over mantendo proporção
        if (gameOverImage != null) {
            // Obter tamanho original da imagem
            float imgWidth = gameOverImage.getWidth();
            float imgHeight = gameOverImage.getHeight();
            
            // Definir largura desejada e calcular altura proporcional
            float desiredWidth = 400;
            float scale = desiredWidth / imgWidth;
            float desiredHeight = imgHeight * scale;
            
            // Centralizar na tela
            float x = (640 - desiredWidth) / 2;
            float y = (480 - desiredHeight) / 2;
            
            game.getBatch().draw(gameOverImage, x, y, desiredWidth, desiredHeight);
        } else {
            // Se não conseguiu carregar a imagem, mostrar texto
            if (font != null) {
                font.draw(game.getBatch(), "GAME OVER", 200, 300);
                font.getData().setScale(1.5f);
                font.draw(game.getBatch(), "Clique para continuar", 150, 200);
                font.getData().setScale(3);
            }
        }
                
        game.getBatch().end();
        
        // Voltar ao menu ao clicar
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
        if (gameOverImage != null) gameOverImage.dispose();
        if (font != null) font.dispose();
    }
}
