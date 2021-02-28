package com.beatype.game.screens.game_screen.notes;

import com.badlogic.gdx.math.Rectangle;

public class Note {

    public double beat;
    public String letter;
    public String type;
    public Rectangle rectangle = new Rectangle();

    public Note (double beat, String letter) {
        this.beat = beat;
        this.letter = letter;
        this.type = "normal";
        this.rectangle.x = 1600;
        this.rectangle.y = 450;
        this.rectangle.height = 64;
        this.rectangle.width = 64;
    }

}
