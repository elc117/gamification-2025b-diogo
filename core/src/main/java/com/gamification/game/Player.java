package com.gamification.game;

public class Player {
    private int lives;

    public Player(int lives, Main game) {
        this.lives = lives; 
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }
}