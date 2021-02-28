package com.beatype.game.screens.game_screen;

import com.badlogic.gdx.utils.Array;
import com.beatype.game.screens.game_screen.notes.Note;

public class Track {

    public Array<Note> notes;

    public double songLength;
    public int songBPM;
    public String sentence, pathToSongFile;

    public Track (double songLength, int songBPM, String sentence, String pathToSongFile, Array<Note> notes) {
        this.songLength = songLength;
        this.songBPM = songBPM;
        this.sentence = sentence;
        this.pathToSongFile = pathToSongFile;
        this.notes = notes;

        notes.add(new Note(2, "T"));
        notes.add(new Note(4, "E"));
        notes.add(new Note(6, "S"));
        notes.add(new Note(8, "T"));
    }

}
