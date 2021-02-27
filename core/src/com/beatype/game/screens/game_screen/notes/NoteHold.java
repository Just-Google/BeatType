package com.beatype.game.screens.game_screen.notes;

public class NoteHold extends Note{

    public double holdEnd;

    public NoteHold (int bpm, String letter, double holdEnd) {
        super(bpm, letter);
        this.type = "hold";
        this.holdEnd = holdEnd;
    }
}
