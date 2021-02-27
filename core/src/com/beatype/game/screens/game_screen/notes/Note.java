package com.beatype.game.screens.game_screen.notes;

public class Note {

    public double bpm;
    public String letter;
    public String type;

    public Note (int bpm, String letter) {
        this.bpm = bpm;
        this.letter = letter;
        this.type = "normal";
    }

}
