package com.gamification.game.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.gamification.game.Main;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
        @Override
        public GwtApplicationConfiguration getConfig () {
            // Tamanho fixo do jogo
            GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(640, 480);
            cfg.padVertical = 0;
            cfg.padHorizontal = 0;
            return cfg;
        }

        @Override
        public ApplicationListener createApplicationListener () {
            Main main = new Main();
            return main;
        }
}
