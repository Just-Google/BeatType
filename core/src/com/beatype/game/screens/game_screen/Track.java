package com.beatype.game.screens.game_screen;

import com.badlogic.gdx.utils.Array;
import com.beatype.game.screens.game_screen.notes.Note;

import java.io.File;

public class Track {

    public Array<Note> notes;

    public double songLength;
    public String sentence, pathToSongFile;

    public Track (double songLength, String sentence, String pathToSongFile, Array<Note> notes) {
        this.songLength = songLength;
        this.sentence = sentence;
        this.pathToSongFile = pathToSongFile;
        this.notes = notes;

        String b = "THIS IS FOR TESTING PURPOSE YES";

        for (int i = 1; i <= b.length(); i++) {
            notes.add(new Note(i, b.substring(i - 1, i)));
        }
    }

    @Override
    public String toString() {
        return "Song length: " + this.songLength + "\nSong Path: " + this.pathToSongFile + "Notes: " + notes;
    }
}
