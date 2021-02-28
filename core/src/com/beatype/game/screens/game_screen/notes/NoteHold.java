package com.beatype.game.screens.game_screen.notes;

public class NoteHold extends Note{

    public double holdEnd;

    public NoteHold (double time, String letter, double holdEnd) {
        super(time, letter);
        this.type = "hold";
        this.holdEnd = holdEnd;
    }
}
