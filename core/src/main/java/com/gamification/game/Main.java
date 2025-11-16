package com.gamification.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background;
    private Texture character;
    private Texture parede;
    private Texture plataforma;
    private Texture andaime;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        character = new Texture("char.png");
        parede = new Texture("parede.png");
        plataforma = new Texture("plataforma.png");
        andaime = new Texture("andaime.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(parede, 0, 0);
        batch.draw(andaime, 20, 0);
        batch.draw(plataforma, 20, 0);
        batch.draw(character, 30, 05); 
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        character.dispose();
        parede.dispose();
        plataforma.dispose();
        andaime.dispose();
    }
}
