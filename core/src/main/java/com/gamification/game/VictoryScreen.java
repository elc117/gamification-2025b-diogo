package com.gamification.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

public class VictoryScreen implements Screen {
    final Main game;
    private Texture background;
    private Texture victoryImage;
    private BitmapFont font;
    private float timer;
    
    public VictoryScreen(Main game) {
        this.game = game;
        this.timer = 0;
    }

    @Override
    public void show() {
        background = new Texture("bg.png");
        try {
            victoryImage = new Texture("parabens.png");
            System.out.println("Victory image loaded: " + victoryImage.getWidth() + "x" + victoryImage.getHeight());
        } catch (Exception e) {
            System.err.println("Erro ao carregar parabens.png: " + e.getMessage());
        }
        font = new BitmapFont();
        font.getData().setScale(3);
    }

    @Override
    public void render(float delta) {
        timer += delta;
        
        ScreenUtils.clear(0.0f, 0.2f, 0.0f, 1f);
        
        game.getBatch().begin();
        
        // Desenhar background escurecido
        game.getBatch().setColor(0.3f, 0.5f, 0.3f, 1f);
        game.getBatch().draw(background, 0, 0, 640, 480);
        
        // Resetar cor para branco
        game.getBatch().setColor(1f, 1f, 1f, 1f);
        
        // Desenhar imagem de vitória mantendo proporção
        if (victoryImage != null) {
            float imgWidth = victoryImage.getWidth();
            float imgHeight = victoryImage.getHeight();
            
            float desiredWidth = 400;
            float scale = desiredWidth / imgWidth;
            float desiredHeight = imgHeight * scale;
            
            float x = (640 - desiredWidth) / 2;
            float y = (480 - desiredHeight) / 2;
            
            game.getBatch().draw(victoryImage, x, y, desiredWidth, desiredHeight);
        } else {
            // Fallback com texto
            if (font != null) {
                font.draw(game.getBatch(), "PARABENS!", 180, 300);
                font.getData().setScale(2f);
                font.draw(game.getBatch(), "Voce completou todas as questoes!", 80, 240);
                font.getData().setScale(1.5f);
                font.draw(game.getBatch(), "Clique para voltar ao menu", 150, 180);
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
        if (victoryImage != null) victoryImage.dispose();
        if (font != null) font.dispose();
    }
}
