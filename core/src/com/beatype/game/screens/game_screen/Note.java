package com.beatype.game.screens.game_screen;

public class Note {

    public double bpm, holdEnd;
    public String letter, type;

    public Note (int bpm, String letter) {
        this.bpm = bpm;
        this.letter = letter;
        this.type = "normal";
    }

    public Note (int bpm, String letter, String type, double holdEnd) {
        this.bpm = bpm;
        this.letter = letter;
        this.type = type;
        this.holdEnd = holdEnd;
    }
}
