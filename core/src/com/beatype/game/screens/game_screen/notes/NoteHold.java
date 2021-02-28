package com.beatype.game.screens.game_screen.notes;

public class NoteHold extends Note{

    public double holdEnd;

    public NoteHold (double beat, String letter, double holdEnd) {
        super(beat, letter);
        this.type = "hold";
        this.holdEnd = holdEnd;
    }
}
