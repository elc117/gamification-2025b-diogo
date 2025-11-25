package com.gamification.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final Main game;
    private Texture background;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private boolean playButtonHovered;

    public MainMenuScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        background = new Texture("bg.png");
        // Temporariamente usando imagens existentes até criar os botões
        playButtonActive = new Texture("plataforma.png");
        playButtonInactive = new Texture("plataforma.png");
        playButtonHovered = false;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0);
        
        // Desenhar botão Play no centro da tela
        float buttonX = Gdx.graphics.getWidth() / 2f - 100;
        float buttonY = Gdx.graphics.getHeight() / 2f - 25;
        
        Texture currentButton = playButtonHovered ? playButtonActive : playButtonInactive;
        game.getBatch().draw(currentButton, buttonX, buttonY, 200, 50);
        
        game.getBatch().end();
        
        // Verificar clique no botão
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            
            if (touchX >= buttonX && touchX <= buttonX + 200 &&
                touchY >= buttonY && touchY <= buttonY + 50) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
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
        if (playButtonActive != null) playButtonActive.dispose();
        if (playButtonInactive != null) playButtonInactive.dispose();
    }
}
