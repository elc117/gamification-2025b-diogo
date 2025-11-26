package com.gamification.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
        playButtonActive = new Texture("play.png");
        playButtonInactive = new Texture("play.png");
        
        // Configurar filtros para melhor qualidade
        background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playButtonActive.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playButtonInactive.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        playButtonHovered = false;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, 640, 480);
        
        // Desenhar botão Play no centro da tela
        Texture currentButton = playButtonHovered ? playButtonActive : playButtonInactive;
        float scale = 0.5f; // Reduzir para metade do tamanho
        float buttonWidth = currentButton.getWidth() * scale;
        float buttonHeight = currentButton.getHeight() * scale;
        float buttonX = (640 - buttonWidth) / 2f;
        float buttonY = (480 - buttonHeight) / 2f;
        
        game.getBatch().draw(currentButton, buttonX, buttonY, buttonWidth, buttonHeight);
        
        game.getBatch().end();
        
        // Verificar clique no botão
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = 480 - Gdx.input.getY(); // Ajustar coordenadas Y
            
            if (touchX >= buttonX && touchX <= buttonX + buttonWidth &&
                touchY >= buttonY && touchY <= buttonY + buttonHeight) {
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
