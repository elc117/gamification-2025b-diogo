package com.gamification.game;

public class Player {
    private int lives;
    private Main game;

    public Player(int lives, Main game) {
        this.game = game;
        this.lives = lives; 
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        if (lives > 1) {
            lives--;
        }else{
            //game.over();
        }
        
    }

}